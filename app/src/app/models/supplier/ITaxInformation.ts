import { VatCondition } from './IVatCondition';

export interface TaxInformation {
  id?: number;
  cuit: string;
  vatCondition: Partial<VatCondition>;
}
