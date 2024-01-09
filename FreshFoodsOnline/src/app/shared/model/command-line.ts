import {Food} from "./food";

export interface CommandLine {
  commandLineId: string,
  product: Food,
  commandId: string,
  unitPrice: number,
  quantity: number,
  totalCommandLinePrice: number
}
