package me.xiongxuan.xoj.entity;

public class TestCase {

    private String output_md5;
    private String stripped_output_md5;
    private Long output_size;
    private Long input_size;
    private String input_name;
    private String output_name;

    @Override
    public String toString() {
        return "TestCase{" +
                "output_md5='" + output_md5 + '\'' +
                ", stripped_output_md5='" + stripped_output_md5 + '\'' +
                ", output_size=" + output_size +
                ", input_size=" + input_size +
                ", input_name='" + input_name + '\'' +
                ", output_name='" + output_name + '\'' +
                '}';
    }

    public String getOutput_md5() {
        return output_md5;
    }

    public void setOutput_md5(String output_md5) {
        this.output_md5 = output_md5;
    }

    public String getStripped_output_md5() {
        return stripped_output_md5;
    }

    public void setStripped_output_md5(String stripped_output_md5) {
        this.stripped_output_md5 = stripped_output_md5;
    }

    public Long getOutput_size() {
        return output_size;
    }

    public void setOutput_size(Long output_size) {
        this.output_size = output_size;
    }

    public Long getInput_size() {
        return input_size;
    }

    public void setInput_size(Long input_size) {
        this.input_size = input_size;
    }

    public String getInput_name() {
        return input_name;
    }

    public void setInput_name(String input_name) {
        this.input_name = input_name;
    }

    public String getOutput_name() {
        return output_name;
    }

    public void setOutput_name(String output_name) {
        this.output_name = output_name;
    }
}
