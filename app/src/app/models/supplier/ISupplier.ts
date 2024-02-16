import { Address } from '../address/IAddress';
import { Image } from '../common/IImage';
import { ContactDetail } from './IContactDetail';
import { Industry } from './IIndustry';
import { TaxInformation } from './ITaxInformation';

export interface Supplier {
  id?: number;
  code: string;
  businessName: string;
  industry: Partial<Industry>;
  website: string;
  email: string;
  phone: string;
  image?: Partial<Image>;
  address: Partial<Address>;
  taxInformation: Partial<TaxInformation>;
  contactDetails: Partial<ContactDetail>;
  isDeleted?: boolean;
}
