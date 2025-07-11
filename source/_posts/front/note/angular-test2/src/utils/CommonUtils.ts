import { IResult, UAParser } from 'ua-parser-js';

export class Utils {
  /**
   *
   */
  public static getUserAgent(): IResult {
    var parser = new UAParser();
    return parser.getResult();
  }
}
