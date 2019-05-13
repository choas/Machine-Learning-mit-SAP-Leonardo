package ml.leonardobuch;

import com.sap.apibhub.sdk.client.ApiException;
import com.sap.apibhub.sdk.ocr_api.model.ResponseJobSubmitted;
import com.sap.apibhub.sdk.ocr_api.model.ResponseResult;
import com.sap.apibhub.sdk.ocr_api.model.ResponseResult.StatusEnum;

/**
 * OCR.
 *
 */
public class App {
  /**
   * Die main Methode.
   * 
   * @param args Parameter
   */
  public static void main(String[] args) throws ApiException, InterruptedException {

    String fileName = Settings.PATH + "ocr/deutsch.png";

    OCR ocr = new OCR();

    OcrOptions options = new OcrOptions();
    options.lang = new String[] { "de" };
    options.outputType = "txt";

    ResponseJobSubmitted job = ocr.ocrJob(fileName, options.options());

    ResponseResult result = null;
    boolean found = false;
    while (!found) {
      System.out.print(".");
      Thread.sleep(1000);
      result = ocr.getOcrJob(job.getId());

      found = (result.getStatus() == StatusEnum.DONE);
    }
    System.out.println("");
    System.out.println(result);
  }

}
