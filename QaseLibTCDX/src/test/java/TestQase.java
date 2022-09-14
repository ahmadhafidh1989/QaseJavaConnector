import org.testng.annotations.Test;
import qase.function.qaseFunction;

import java.io.IOException;

public class TestQase {
    qaseFunction func = new qaseFunction("13","c5bb4e2d302352e9d4ababe9f405580d7ce792f6",
            "KRIP", "KRIP",false);

    @Test
    public void CreateTestRun() throws IOException {
        //Integer aa = func.GetRunId();
        func.PostResult(14,239,"passed", "kkkkk");
    }


}
