package qase.model.responses;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestRunDto {
    public boolean status;
    @JsonProperty("Result")
    public RunResult result;
}
