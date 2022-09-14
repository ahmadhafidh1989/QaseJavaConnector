package qase.model.requests;

import java.util.ArrayList;

public class CreateRunDto {
    public ArrayList<Integer> cases;
    public String title;

    public CreateRunDto(ArrayList<Integer> cases, String title, boolean include_all_cases) {
        this.cases = cases;
        this.title = title;
        this.include_all_cases = include_all_cases;
    }

    public boolean include_all_cases;
}
