import {Customer} from "./customer";
import {CommandLine} from "./command-line";

export interface Command {
  commandId: string,
  customer: Customer,
  totalCommandPrice: number,
  commandDate: string,
  commandStatus: string,
  lastModifiedDate: string
}
