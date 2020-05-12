package tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.AllUseCasesResponse;
import rs.htec.apps.qasandbox.qa.model.DeleteUseCaseResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import utils.Methods;
import utils.User;

public class DeleteUseCase {

  private String username;
  private String password;
  private String token;
  private DeleteUseCaseResponse dUCR;

  @BeforeClass
  public void init() throws Exception {
    //get token:
    username = User.USER_ZELJKO.getUsername();
    password = User.USER_ZELJKO.getPassword();
    LoginResponse loginResponse = Methods.login(username, password);
    token = loginResponse.getToken();
  }

  @Test
  public void deleteAllUseCases() throws Exception {
    //get all use cases:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    //get use case Ids:
    List<Integer> useCaseIds = new ArrayList<>();
    for (AllUseCasesResponse aUCR : allUseCases) {
      useCaseIds.add(aUCR.getUsecaseId());
    }
    //delete all use cases:
    for (Integer uCI : useCaseIds) {
      dUCR = Methods.deleteUseCase(uCI, token);
      AssertJUnit.assertEquals("Entry is not removed","Entry removed successfully", dUCR.getSucess());
    }
    //verify that all use cases are deleted:
    Methods.verifyThatAllUseCasesAreDeleted(token);
  }
}
