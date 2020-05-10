package tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rs.htec.apps.qasandbox.qa.model.AllUseCasesResponse;
import rs.htec.apps.qasandbox.qa.model.EditUseCaseResponse;
import rs.htec.apps.qasandbox.qa.model.GetUseCaseResponse;
import rs.htec.apps.qasandbox.qa.model.LoginResponse;
import utils.Methods;
import utils.User;

public class EditUseCase {

  private String username;
  private String password;
  private String token;
  private GetUseCaseResponse getUseCaseResponse;
  private String editString;

  @BeforeClass
  public void init () throws Exception {
    username = User.USER_ZELJKO.getUsername();
    password = User.USER_ZELJKO.getPassword();
    LoginResponse loginResponse = Methods.login(username, password);
    token = loginResponse.getToken();
    editString = " edited";
  }

  @Test
  public void editAllUseCases() throws Exception {
    //get all use cases:
    List<AllUseCasesResponse> allUseCases = Methods.retrieveAllUseCases(token);
    //get use case Ids:
    List<Integer> useCaseIds = new ArrayList<>();
    for (AllUseCasesResponse aUCR : allUseCases) {
      useCaseIds.add(aUCR.getUsecaseId());
    }
    //edit all use cases:
    for (Integer uCI : useCaseIds) {
      getUseCaseResponse = Methods.getUseCase(token, uCI);
      EditUseCaseResponse editUseCaseResponse = Methods.editEveryPropertyInUseCase(token, uCI, getUseCaseResponse, editString);
      //verify that use case is edited:
      AssertJUnit.assertEquals("Title is not edited", getUseCaseResponse.getTitle() + editString, editUseCaseResponse.getTitle());
      AssertJUnit.assertEquals("Description is not edited", getUseCaseResponse.getDescription() + editString, editUseCaseResponse.getDescription());
      AssertJUnit.assertEquals("Expected result is not edited", getUseCaseResponse.getExpectedResult() + editString, editUseCaseResponse.getExpectedResult());
      for (int i = 0; i < editUseCaseResponse.getTeststeps().size(); i++) {
        AssertJUnit.assertEquals("Test step is not edited", getUseCaseResponse.getTeststeps().get(i) + editString, editUseCaseResponse.getTeststeps().get(i));
      }
      AssertJUnit.assertEquals("Title is not edited", getUseCaseResponse.getTitle() + editString, editUseCaseResponse.getTitle());
    }
  }

}
