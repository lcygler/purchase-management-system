import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Country } from 'src/app/models/address/ICountry';
import { State } from 'src/app/models/address/IState';
import { Image } from 'src/app/models/common/IImage';
import { Industry } from 'src/app/models/supplier/IIndustry';
import { Supplier } from 'src/app/models/supplier/ISupplier';
import { VatCondition } from 'src/app/models/supplier/IVatCondition';

import { AddressService } from 'src/app/services/address/address.service';
import { CountryService } from 'src/app/services/address/country.service';
import { StateService } from 'src/app/services/address/state.service';
import { ImageService } from 'src/app/services/common/image.service';
import { ToastService } from 'src/app/services/common/toast.service';
import { ContactDetailService } from 'src/app/services/supplier/contact-detail.service';
import { IndustryService } from 'src/app/services/supplier/industry.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';
import { TaxInformationService } from 'src/app/services/supplier/tax-information.service';
import { VatConditionService } from 'src/app/services/supplier/vat-condition.service';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css'],
})
export class SuppliersFormComponent implements OnInit {
  supplier: Supplier = {
    code: '',
    businessName: '',
    industry: {},
    website: '',
    email: '',
    phone: '',
    image: {},
    address: {
      state: {
        country: {},
      },
    },
    taxInformation: {
      vatCondition: {},
    },
    contactDetails: {},
  };

  supplierList: Supplier[] = [];
  vatConditionList: VatCondition[] = [];
  industryList: Industry[] = [];
  countryList: Country[] = [];
  stateList: State[] = [];
  filteredStates: State[] = [];

  supplierId: number | null = null;
  newAddressId: number | null = null;
  newTaxInformationId: number | null = null;
  newContactDetailId: number | null = null;
  isEditView: boolean = false;
  isImageValid: boolean = true;

  placeholder: string =
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1706758794/asj/suppliers/supplier-placeholder_zhufia.png';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService,
    private toastService: ToastService,
    private addressService: AddressService,
    private stateService: StateService,
    private countryService: CountryService,
    private taxInformationService: TaxInformationService,
    private vatConditionService: VatConditionService,
    private contactDetailService: ContactDetailService,
    private industryService: IndustryService,
    private location: Location,
    private imageService: ImageService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.supplierId = parseInt(id);
      this.getSupplierById(this.supplierId);
      this.isEditView = this.isEditRoute();
    }

    if (!this.isEditView) {
      this.getSupplierCode();
    }

    this.countryService.getCountries().subscribe((res) => {
      this.countryList = res;
    });

    this.getIndustries();
    this.getVatConditions();
    this.getSuppliers();
  }

  getSupplierById(id: number) {
    if (id) {
      this.supplierService.getSupplierById(id).subscribe({
        next: (res) => {
          this.supplier = res;
          const countryId = this.supplier?.address?.state?.country?.id;
          countryId && this.getStatesByCountry(countryId);
        },
        error: (error) => {
          if (error.status === 404) {
            this.router.navigate(['/not-found']);
          }
        },
      });
    }
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res;
    });
  }

  getSupplierCode() {
    this.supplierService.getSupplierCode().subscribe((res) => {
      this.supplier.code = res;
    });
  }

  getVatConditions() {
    this.vatConditionService.getVatConditions().subscribe((res) => {
      this.vatConditionList = res;
    });
  }

  getIndustries() {
    this.industryService.getIndustries().subscribe((res) => {
      this.industryList = res.filter((i) => !i.isDeleted);
    });
  }

  getStatesByCountry(countryId: number) {
    this.stateService.getStatesByCountry(countryId).subscribe((res) => {
      this.filteredStates = res;
    });
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    const address = {
      street: formData.street,
      number: formData.number,
      postalCode: formData.postalCode,
      city: formData.city,
      state: { id: formData.state },
    };

    const taxInformation = {
      cuit: formData.cuit,
      vatCondition: { id: formData.vatCondition },
    };

    const contactDetails = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      phone: formData.contactPhone,
      email: formData.contactEmail,
      role: formData.role,
    };

    const supplier: Supplier = {
      code: formData.code,
      businessName: formData.businessName,
      industry: { id: formData.industry },
      website: formData.website,
      email: formData.email,
      phone: formData.phone,
      address: {},
      taxInformation: {},
      contactDetails: {},
    };

    const image: Image = {
      url: formData.image || this.placeholder,
    };

    if (this.isEditRoute()) {
      // Update address
      this.addressService
        .updateAddress(this.supplier.address.id!, address)
        .subscribe((res) => {
          this.newAddressId = res.id!;

          // Update tax information
          this.taxInformationService
            .updateTaxInformation(
              this.supplier.taxInformation.id!,
              taxInformation
            )
            .subscribe((res) => {
              this.newTaxInformationId = res.id!;

              // Update contact details
              this.contactDetailService
                .updateContactDetail(
                  this.supplier.contactDetails.id!,
                  contactDetails
                )
                .subscribe((res) => {
                  this.newContactDetailId = res.id!;

                  // Update logo
                  this.imageService
                    .updateImage(this.supplier.image?.id!, image)
                    .subscribe((res) => {
                      // Update supplier
                      supplier.address = { id: this.newAddressId! };
                      supplier.taxInformation = {
                        id: this.newTaxInformationId!,
                      };
                      supplier.contactDetails = {
                        id: this.newContactDetailId!,
                      };
                      supplier.image = { id: res.id };

                      this.supplierService
                        .updateSupplier(this.supplierId!, supplier)
                        .subscribe((res) => {
                          this.toastService.showSuccessToast(
                            'Proveedor modificado correctamente!'
                          );
                          form.reset();
                          this.newAddressId = null;
                          this.newTaxInformationId = null;
                          this.newContactDetailId = null;
                          this.navigateToSuppliers();
                        });
                    });
                });
            });
        });
    } else {
      // Add address
      this.addressService.createAddress(address).subscribe((res) => {
        this.newAddressId = res.id!;

        // Add tax information
        this.taxInformationService
          .createTaxInformation(taxInformation)
          .subscribe((res) => {
            this.newTaxInformationId = res.id!;

            // Add contact details
            this.contactDetailService
              .createContactDetail(contactDetails)
              .subscribe((res) => {
                this.newContactDetailId = res.id!;

                // Add image
                this.imageService.createImage(image).subscribe((res) => {
                  supplier.address = { id: this.newAddressId! };
                  supplier.taxInformation = { id: this.newTaxInformationId! };
                  supplier.contactDetails = { id: this.newContactDetailId! };
                  supplier.image = { id: res.id };

                  // Add supplier
                  this.supplierService
                    .createSupplier(supplier)
                    .subscribe((res) => {
                      this.toastService.showSuccessToast(
                        'Proveedor agregado correctamente!'
                      );
                      form.reset();
                      this.newAddressId = null;
                      this.newTaxInformationId = null;
                      this.newContactDetailId = null;
                      this.navigateToSuppliers();
                    });
                });
              });
          });
      });
    }
  }

  navigateToSuppliers() {
    this.location.back();
    // this.router.navigate(['/suppliers']);
  }

  navigateBack() {
    this.location.back();
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/suppliers/edit');
  }

  onCountryChange(selectedCountryId: number) {
    if (selectedCountryId) {
      this.getStatesByCountry(selectedCountryId);
    } else {
      this.filteredStates = [];
    }
    this.supplier.address.state!.id = null;
  }

  formatCuit(event: any) {
    const cuit = event.target.value.replace(/\D/g, ''); // Eliminar no dígitos
    const maxLength = 11;

    if (cuit.length <= maxLength) {
      if (cuit.length <= 2) {
        this.supplier.taxInformation.cuit = cuit;
      } else if (cuit.length <= 10) {
        this.supplier.taxInformation.cuit = `${cuit.substring(
          0,
          2
        )}-${cuit.substring(2, 10)}`;
      } else {
        this.supplier.taxInformation.cuit = `${cuit.substring(
          0,
          2
        )}-${cuit.substring(2, 10)}-${cuit.substring(10, 11)}`;
      }
    }
  }

  isCodeValid(): boolean {
    return !this.supplierList.some(
      (s) => s.code === this.supplier.code && s.id !== this.supplier.id
    );
  }

  isCuitValid(): boolean {
    return !this.supplierList.some(
      (s) =>
        s.taxInformation.cuit === this.supplier.taxInformation.cuit &&
        s.id !== this.supplier.id
    );
  }

  setImageError() {
    this.isImageValid = false;
  }

  resetImageError() {
    this.isImageValid = true;
  }
}
