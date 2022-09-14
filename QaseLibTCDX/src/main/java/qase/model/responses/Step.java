package qase.model.responses;

import java.util.ArrayList;

public class Step {
    public String hash;
    public int position;
    public Object shared_step_hash;
    public Object shared_step_nested_hash;
    public ArrayList<Object> attachments;
    public String action;
    public Object expected_result;
    public Object data;
}
