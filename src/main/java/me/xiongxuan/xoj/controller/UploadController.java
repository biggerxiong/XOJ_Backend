package me.xiongxuan.xoj.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.xiongxuan.xoj.entity.Problem;
import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.entity.TestCase;
import me.xiongxuan.xoj.enums.ResultEnum;
import me.xiongxuan.xoj.service.ProblemService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongXuan
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    /**
     * 存放测试数据压缩文件的位置
     */
    @Value("${file.upload.path.test-case}")
    private String testCaseUploadPath;

    @Value("${file.upload.path.file-name-max-length}")
    private Integer fileNameMaxLength;

    /**
     * 存放处理后测试数据的位置
     */
    @Value("${file.test-case.path}")
    private String testCasePath;

    /**
     * 临时文件位置
     */
    @Value("${file.test-case.temp-path}")
    private String testCaseTempPath;

    @Resource
    private ProblemService problemService;


    @PreAuthorize("hasAuthority('AUTH_UPLOAD_DATA')")
    @PostMapping("/data/{problemId}")
    @ResponseBody
    public Result singleFileUpload(@RequestParam("data") MultipartFile file, @PathVariable Integer problemId) {

        if (file.isEmpty()) {
            return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_PROBLEM_DATA_NULL);
        }

        if(file.getOriginalFilename() != null && file.getOriginalFilename().length() > fileNameMaxLength) {
            return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_PROBLEM_DATA_NAME_LENGTH_LIMIT);
        }

        Problem problem = problemService.getProblemById(problemId);

        // 如果之前上传过，那么删除旧的
        File oldFile = new File(testCaseUploadPath + File.separator + problem.getProblemId() + ".zip");
        if(oldFile.exists()) {
            boolean delete = oldFile.delete();
            if (!delete) {
                return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_PROBLEM_DATA_DELETE_FAILED);
            }
        }

        try {
            // Get the file and save it somewhere
            Path path = Paths.get(testCaseUploadPath + File.separator + problem.getProblemId() + ".zip");
            File saveFile = path.toFile();

            if(!saveFile.getParentFile().exists()) {
                boolean mkdirs = saveFile.getParentFile().mkdirs();
            }
            file.transferTo(path.toFile());

            processTestCase(path.toString(), testCasePath + File.separator + problemId, testCaseTempPath + File.separator + problemId);

            return ResultUtil.getNoObjectInstance(ResultEnum.SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(-1, e.toString());
        }
    }

    public void processTestCase(String zipFilePath, String savedPath, String saveTempPath) throws IOException {
        // 解压到临时文件夹
        File unzipFilePath = ZipUtil.unzip(zipFilePath, saveTempPath);

        // 构建匹配器，用来匹配所有后缀为.in的文件
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.in");
        List<TestCase> testCaseList = new ArrayList<>();

        // 遍历所有文件，如果找到一个后缀为.in的文件，就找它是否有对应的.out文件
        // 如果有的话，就读取输出文件，获取到它的md5、去除行末空格的md5、文件大小，放入对应的testCase中
        Files.walkFileTree(unzipFilePath.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (!unzipFilePath.toPath().equals(dir)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {

                if (matcher.matches(path)) {
                    String inputFileName = path.getFileName().toString();
                    String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".in")) + ".out";
                    String inputFilePath = path.getParent() + File.separator + inputFileName;
                    String outputFilePath = path.getParent() + File.separator + outputFileName;

                    // 默认以UTF-8读取
                    FileReader fileReader = new FileReader(outputFilePath, Charset.forName("UTF-8"));
                    String output = fileReader.readString();
                    // 将windows下的换行转换成linux下的换行
                    output = output.replaceAll("\r\n", "\n");
                    // 去除行尾空格和换行
                    String strippedOutput = StrUtil.strip(output, "", " ");
                    strippedOutput = StrUtil.strip(strippedOutput, "", "\n");

                    TestCase testCase = new TestCase();
                    testCase.setInput_name(inputFileName);
                    testCase.setInput_size(FileUtil.size(new File(inputFilePath)));
                    testCase.setOutput_name(outputFileName);
                    testCase.setOutput_size(FileUtil.size(fileReader.getFile()));
                    testCase.setOutput_md5(DigestUtil.md5Hex(output));
                    testCase.setStripped_output_md5(DigestUtil.md5Hex(strippedOutput));
                    testCaseList.add(testCase);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        // 遍历所有的testCase，生成对应的json
        JSONObject jsonTestCase = new JSONObject();
        for (int i = 0; i < testCaseList.size(); i++) {
            System.out.println(testCaseList.get(i));
            jsonTestCase.put(Integer.toString(i + 1), JSONUtil.parse(testCaseList.get(i)));
        }
        // 生成info文件的json
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("test_case_number", testCaseList.size());
        jsonInfo.put("spj", false);
        jsonInfo.put("test_cases", jsonTestCase);

        // 写入到文件中
        FileWriter fileWriter = new FileWriter(saveTempPath + File.separator + "info", Charset.forName("UTF-8"));
        fileWriter.write(jsonInfo.toString());

        // 如果原来的文件夹存在，就删除
//        if (FileUtil.file(savedPath).exists()) {
//            FileUtil.del(savedPath);
//        }
        // 将文件从临时目录移动到正式目录
        FileUtil.move(FileUtil.file(saveTempPath), FileUtil.file(savedPath), true);
    }
}
