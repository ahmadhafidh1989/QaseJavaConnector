package qase.function;

import com.google.gson.Gson;
import okhttp3.*;
import qase.model.requests.CreateRunDto;
import qase.model.requests.CreateRunResultDto;
import qase.model.responses.Entity;
import qase.model.responses.TestCasesColDto;
import qase.model.responses.TestRunDto;

import java.io.IOException;
import java.util.ArrayList;

public class qaseFunction {
    String suiteId;
    String Token;
    String TestRunTitle;
    String ProjectCode;
    Boolean include_all_cases = false;
    String TestCasesData;
    String CaseId;
    Integer TestRunId;

    public qaseFunction(String suiteId, String token, String testRunTitle,String ProjectCode, Boolean include_all_cases) {
        this.suiteId = suiteId;
        Token = token;
        TestRunTitle = testRunTitle;
        this.include_all_cases = include_all_cases;
        this.ProjectCode = ProjectCode;
    }

    public String GetTestCases() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.qase.io/v1/case/" + this.ProjectCode +"?suite_id=" + this.suiteId +"&limit=100&offset=0")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Token", this.Token)
                .build();

        Response response = client.newCall(request).execute();
        return TestCasesData = response.body().string();
    }

    public Integer PostNewRunApi() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, CaseId);
        Request request = new Request.Builder()
                .url("https://api.qase.io/v1/run/" + this.ProjectCode)
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Token", this.Token)
                .build();

        Response response = client.newCall(request).execute();

        String rspn = response.body().string();
        Gson gson = new Gson();
        TestRunDto runDto = gson.fromJson(rspn,TestRunDto.class);
        return TestRunId = runDto.result.id;
    }

    public Integer GetRunId() throws IOException {
        //Get all test cases base on the project id
        GetTestCases();
        ArrayList<Integer> CasesIdList = new ArrayList<>();
        Gson gson = new Gson();
        TestCasesColDto testCasesColDto = gson.fromJson(TestCasesData, TestCasesColDto.class);
        for (Entity entity : testCasesColDto.result.entities) {
            CasesIdList.add(entity.id);
        }
        CreateRunDto testRun = new CreateRunDto(CasesIdList,"TestFromCode", false);
        CaseId = gson.toJson(testRun);

        PostNewRunApi();
        return TestRunId;
    }

    public void PostResult(Integer runId, Integer caseId, String status, String comment) throws IOException {
        CreateRunResultDto runResult = new CreateRunResultDto(caseId,status, comment);
        Gson gson = new Gson();
        String runRslt = gson.toJson(runResult);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, runRslt);
        Request request = new Request.Builder()
                .url("https://api.qase.io/v1/result/" + this.ProjectCode +"/" + runId)
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Token", "c5bb4e2d302352e9d4ababe9f405580d7ce792f6")
                .build();

        Response response = client.newCall(request).execute();
        String rspn = response.body().string();

    }


}
