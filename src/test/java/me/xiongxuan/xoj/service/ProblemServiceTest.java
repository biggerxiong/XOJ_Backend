package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Problem;
import me.xiongxuan.xoj.entity.ProblemType;
import me.xiongxuan.xoj.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class ProblemServiceTest {
    @Autowired
    private ProblemService problemService;
    @Before
    public void setup() {

        System.out.println("setup");
    }

    @After
    public void tearDown() {

        System.out.println("tearDown");
    }

    @Test
    public void getProblemById() {
//        System.out.println(problemService.getProblemById(1001));
    }

    @Test
    public void getProblemList() {
//        System.out.println(problemService.getVisiableProblemList());
    }

    @Test
    public void htmlToMk() {
        for (int i = 1002; i <= 1048; i++) {
            Problem problem = problemService.getTotalProblemById(i);

            String descriptionMarkdown = problem.getDescription();
            if (descriptionMarkdown != null) {
                descriptionMarkdown = descriptionMarkdown.replaceAll("<p>", "");
                descriptionMarkdown = descriptionMarkdown.replaceAll("</p>", "\n");
                descriptionMarkdown = descriptionMarkdown.replaceAll("<pre>", "**");
                descriptionMarkdown = descriptionMarkdown.replaceAll("</pre>", "**");
                descriptionMarkdown = descriptionMarkdown.replaceAll("<strong>", "**");
                descriptionMarkdown = descriptionMarkdown.replaceAll("</strong>", "**");
            }
            problem.setDescriptionMarkdown(descriptionMarkdown);

            String inputDescriptionMarkdown = problem.getInputDescription();
            if (inputDescriptionMarkdown != null) {
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("<p>", "");
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("</p>", "\n");
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("<pre>", "**");
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("</pre>", "**");
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("<strong>", "**");
                inputDescriptionMarkdown = inputDescriptionMarkdown.replaceAll("</strong>", "**");
            }
            problem.setInputDescriptionMarkdown(inputDescriptionMarkdown);

            String outputDescriptionMarkdown = problem.getOutputDescription();
            if (outputDescriptionMarkdown != null) {
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("<p>", "");
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("</p>", "\n");
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("<pre>", "**");
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("</pre>", "**");
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("<strong>", "**");
                outputDescriptionMarkdown = outputDescriptionMarkdown.replaceAll("</strong>", "**");
            }
            problem.setOutputDescriptionMarkdown(outputDescriptionMarkdown);

            String hintMarkdown = problem.getHint();
            if (hintMarkdown != null) {
                hintMarkdown = hintMarkdown.replaceAll("<p>", "");
                hintMarkdown = hintMarkdown.replaceAll("</p>", "");
                hintMarkdown = hintMarkdown.replaceAll("<pre>", "**");
                hintMarkdown = hintMarkdown.replaceAll("</pre>", "**");
                hintMarkdown = hintMarkdown.replaceAll("<strong>", "**");
                hintMarkdown = hintMarkdown.replaceAll("</strong>", "**");
            }
            problem.setHintMarkdown(hintMarkdown);

            problemService.updateProblem(problem);
        }
    }

    @Test
    public void createProblem() {
        // testcaseCount: 5, timeLimit: 1000, memoryLimit: 128},

//        Problem problem = new Problem();
//        problem.setTitle("LC的疑惑");
//        problem.setDescription("<p style=\\\"margin-left: 40px;\\\">题意很简单：         </p><p style=\\\"margin-left: 80px;\\\">LC现在得到了一串不相同的数字， 现在让你找出这串数字中最小的数和次小的数。</p>");
//        problem.setDescriptionMarkdown("<p style=\\\"margin-left: 40px;\\\">题意很简单：         </p><p style=\\\"margin-left: 80px;\\\">LC现在得到了一串不相同的数字， 现在让你找出这串数字中最小的数和次小的数。</p>");
//        problem.setInputDescription("第一行输入一个整数n 且 2&lt;=n&lt;=1000;\\n第二行输入n个数字(A1, A2, A3 .... An)   对任意的Ai, 0&lt;=Ai &lt;= 100000;且各个数字各不相同");
//        problem.setInputDescriptionMarkdown("第一行输入一个整数n 且 2&lt;=n&lt;=1000;\\n第二行输入n个数字(A1, A2, A3 .... An)   对任意的Ai, 0&lt;=Ai &lt;= 100000;且各个数字各不相同");
//        problem.setOutputDescription("第一行输出两个数a,b    (a代表最小的数， b代表次小的数) 中间以空格隔开。");
//        problem.setOutputDescriptionMarkdown("第一行输出两个数a,b    (a代表最小的数， b代表次小的数) 中间以空格隔开。");
//        problem.setSamples("[{\"input\": \"3 \n3 1 2\", \"output\": \"1 2\"}]");
//        problem.setHint("");
//        problem.setHintMarkdown("");
//        problem.setSubmit(0);
//        problem.setAccept(0);
//        problem.setCreateBy(new User(2));
//        problem.setTimeLimit(1000);
//        problem.setMemoryLimit(128);
//        problem.setProblemType(new ProblemType(2));
//        problem.setRemoteProblemId("15");
//
//        problemService.createProblem(problem);

//        problem = new Problem();
//        problem.setTitle("A+B problem");
//        problem.setDescription("<p>请计算两个整数的和并输出结果。</p><p>注意不要有不必要的输出，比如&quot;请输入 a 和 b 的值: &quot;，示例代码见隐藏部分。</p>");
//        problem.setDescriptionMarkdown("<p>请计算两个整数的和并输出结果。</p><p>注意不要有不必要的输出，比如&quot;请输入 a 和 b 的值: &quot;，示例代码见隐藏部分。</p>");
//        problem.setInputDescription("<p>两个用空格分开的整数.</p>");
//        problem.setInputDescriptionMarkdown("<p>两个用空格分开的整数.</p>");
//        problem.setOutputDescription("<p>两数之和</p>");
//        problem.setOutputDescriptionMarkdown("<p>两数之和</p>");
//        problem.setSamples("[{\"input\": \"1 1\", \"output\": \"2\"},{\"input\": \"2 1\", \"output\": \"3\"},{\"input\": \"2 2\", \"output\": \"4\"}]");
//        problem.setHint("<p>C 语言实现:</p><pre><code class=\"lang-c++\">#include &lt;stdio.h&gt;    \\nint main(){\\n    int a, b;\\n    scanf(&quot;%d%d&quot;, &a, &b);\\n    printf(&quot;%d\\\\n&quot;, a+b);\\n    return 0;\\n}</code></pre><p>Java 实现:</p><pre><code class=\"lang-java\">import java.util.Scanner;\\npublic class Main{\\n    public static void main(String[] args){\\n        Scanner in=new Scanner(System.in);\\n        int a=in.nextInt();\\n        int b=in.nextInt();\\n        System.out.println((a+b));  \\n    }\\n}</code></pre>");
//        problem.setHintMarkdown("<p>C 语言实现:</p><pre><code class=\"lang-c++\">#include &lt;stdio.h&gt;    \\nint main(){\\n    int a, b;\\n    scanf(&quot;%d%d&quot;, &a, &b);\\n    printf(&quot;%d\\\\n&quot;, a+b);\\n    return 0;\\n}</code></pre><p>Java 实现:</p><pre><code class=\"lang-java\">import java.util.Scanner;\\npublic class Main{\\n    public static void main(String[] args){\\n        Scanner in=new Scanner(System.in);\\n        int a=in.nextInt();\\n        int b=in.nextInt();\\n        System.out.println((a+b));  \\n    }\\n}</code></pre>");
//        problem.setSubmit(0);
//        problem.setAccept(0);
//        problem.setCreateBy(new User(2));
//        problem.setTimeLimit(1000);
//        problem.setMemoryLimit(128);
//        problem.setProblemType(new ProblemType(1));
//
//        problemService.createProblem(problem);

//        problem = new Problem();
//        problem.setTitle("test3");
//        problem.setDescription("<p>请计算两个整数的和并输出结果。</p><p>注意不要有不必要的输出，比如&quot;请输入 a 和 b 的值: &quot;，示例代码见隐藏部分。</p>");
//        problem.setDescriptionMarkdown("<p>请计算两个整数的和并输出结果。</p><p>注意不要有不必要的输出，比如&quot;请输入 a 和 b 的值: &quot;，示例代码见隐藏部分。</p>");
//        problem.setInputDescription("<p>两个用空格分开的整数.</p>");
//        problem.setInputDescriptionMarkdown("<p>两个用空格分开的整数.</p>");
//        problem.setOutputDescription("<p>两数之和</p>");
//        problem.setOutputDescriptionMarkdown("<p>两数之和</p>");
//        problem.setSamples("[{\"input\": \"1 1\", \"output\": \"2\"},{\"input\": \"2 1\", \"output\": \"3\"},{\"input\": \"2 2\", \"output\": \"4\"}]");
//        problem.setHint("<p>C 语言实现:</p><pre><code class=\"lang-c++\">#include &lt;stdio.h&gt;    \\nint main(){\\n    int a, b;\\n    scanf(&quot;%d%d&quot;, &a, &b);\\n    printf(&quot;%d\\\\n&quot;, a+b);\\n    return 0;\\n}</code></pre><p>Java 实现:</p><pre><code class=\"lang-java\">import java.util.Scanner;\\npublic class Main{\\n    public static void main(String[] args){\\n        Scanner in=new Scanner(System.in);\\n        int a=in.nextInt();\\n        int b=in.nextInt();\\n        System.out.println((a+b));  \\n    }\\n}</code></pre>");
//        problem.setHintMarkdown("<p>C 语言实现:</p><pre><code class=\"lang-c++\">#include &lt;stdio.h&gt;    \\nint main(){\\n    int a, b;\\n    scanf(&quot;%d%d&quot;, &a, &b);\\n    printf(&quot;%d\\\\n&quot;, a+b);\\n    return 0;\\n}</code></pre><p>Java 实现:</p><pre><code class=\"lang-java\">import java.util.Scanner;\\npublic class Main{\\n    public static void main(String[] args){\\n        Scanner in=new Scanner(System.in);\\n        int a=in.nextInt();\\n        int b=in.nextInt();\\n        System.out.println((a+b));  \\n    }\\n}</code></pre>");
//        problem.setSubmit(0);
//        problem.setAccept(0);
//        problem.setCreateBy(new User(2));
//        problem.setTimeLimit(1000);
//        problem.setMemoryLimit(128);
//        problem.setProblemType(new ProblemType(1));
//
//        problemService.createProblem(problem);
    }
}