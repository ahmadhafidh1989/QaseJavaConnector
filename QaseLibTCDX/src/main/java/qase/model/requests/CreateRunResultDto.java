package qase.model.requests;

public class CreateRunResultDto {
    public int case_id;
    public String status;
    public String comment;

    public CreateRunResultDto(int case_id, String status, String comment) {
        this.case_id = case_id;
        this.status = status;
        this.comment = comment;
    }
}
