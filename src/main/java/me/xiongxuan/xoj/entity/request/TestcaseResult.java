package me.xiongxuan.xoj.entity.request;

public class TestcaseResult {
    private Integer memory;
    private String outputMd5;
    private String output;
    private Integer realTime;
    private Integer signal;
    private Integer cpuTime;
    private String testCase;
    private Integer result;
    private Integer exitCode;
    private Integer error;

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getOutputMd5() {
        return outputMd5;
    }

    public void setOutputMd5(String outputMd5) {
        this.outputMd5 = outputMd5;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getRealTime() {
        return realTime;
    }

    public void setRealTime(Integer realTime) {
        this.realTime = realTime;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Integer getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(Integer cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "TestcaseResult{" +
                "memory=" + memory +
                ", outputMd5='" + outputMd5 + '\'' +
                ", output='" + output + '\'' +
                ", realTime=" + realTime +
                ", signal=" + signal +
                ", cpuTime=" + cpuTime +
                ", testCase='" + testCase + '\'' +
                ", result=" + result +
                ", exitCode=" + exitCode +
                ", error=" + error +
                '}';
    }
}
