import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Category } from 'src/app/models/product/ICategory';
import { Industry } from 'src/app/models/supplier/IIndustry';

import { ToastService } from 'src/app/services/common/toast.service';
import { CategoryService } from 'src/app/services/product/category.service';
import { IndustryService } from 'src/app/services/supplier/industry.service';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css'],
})
export class ManagementComponent implements OnInit {
  id: number | null = null;

  category: Category = {
    name: '',
  };

  industry: Industry = {
    name: '',
  };

  categoryList: Category[] = [];
  industryList: Industry[] = [];

  isCategoryEdit: boolean = false;
  isIndustryEdit: boolean = false;
  breadcrumb: string = '';
  deleteMessage: string = '';
  restoreMessage: string = '';
  idToDelete: number | null = null;
  idToRestore: number | null = null;
  isLoadingCategories: boolean = true;
  isLoadingIndustries: boolean = true;

  categorySort: string = 'Default';
  industrySort: string = 'Default';
  deleteCategoryFilter: boolean = false;
  deleteIndustryFilter: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastService,
    private categoryService: CategoryService,
    private industryService: IndustryService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getUrlParams();
    this.getCategories();
    this.getIndustries();
  }

  getUrlParams() {
    this.route.paramMap.subscribe((paramMap: any) => {
      this.id = parseInt(paramMap.get('id'));

      if (this.id) {
        this.isCategoryEdit = this.isCategoryRoute();
        this.isIndustryEdit = this.isIndustryRoute();
      }

      if (this.isCategoryEdit) {
        this.getCategoryById();
      } else if (this.isIndustryEdit) {
        this.getIndustryById();
      }
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categoryList = res;

      setTimeout(() => {
        this.isLoadingCategories = false;
      }, 500);
    });
  }

  getIndustries() {
    this.industryService.getIndustries().subscribe((res) => {
      this.industryList = res;

      setTimeout(() => {
        this.isLoadingIndustries = false;
      }, 500);
    });
  }

  getCategoryById() {
    this.categoryService.getCategoryById(this.id!).subscribe((res) => {
      this.category = res;
      this.breadcrumb = res.name;
    });
  }

  getIndustryById() {
    this.industryService.getIndustryById(this.id!).subscribe((res) => {
      this.industry = res;
      this.breadcrumb = res.name;
    });
  }

  onCategorySubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    const category: Category = {
      name: formData.categoryName.trim(),
    };

    if (this.isCategoryEdit) {
      // Update category
      this.categoryService
        .updateCategory(this.id!, category)
        .subscribe((res) => {
          this.toastService.showSuccessToast(
            'Categoría modificada correctamente!'
          );
          form.reset();
          this.getCategories();
          this.navigateToCategories();
        });
    } else {
      // Add category
      this.categoryService.createCategory(category).subscribe((res) => {
        this.toastService.showSuccessToast('Categoría agregada correctamente!');
        form.reset();
        this.getCategories();
      });
    }
  }

  onIndustrySubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    const industry: Industry = {
      name: formData.industryName.trim(),
    };

    if (this.isIndustryEdit) {
      // Update industry
      this.industryService
        .updateIndustry(this.id!, industry)
        .subscribe((res) => {
          this.toastService.showSuccessToast('Rubro modificado correctamente!');
          form.reset();
          this.getIndustries();
          this.navigateToIndustries();
        });
    } else {
      // Add industry
      this.industryService.createIndustry(industry).subscribe((res) => {
        this.toastService.showSuccessToast('Rubro agregado correctamente!');
        form.reset();
        this.getIndustries();
      });
    }
  }

  navigateBack() {
    this.location.back();
  }

  navigateToManagement() {
    this.router.navigate([`/management`]);
  }

  navigateToCategories() {
    this.router.navigate([`/management/categories`]);
  }

  navigateToIndustries() {
    this.router.navigate([`/management/industries`]);
  }

  editCategory(id: number) {
    this.router.navigate([`/management/categories/${id}`]);
  }

  editIndustry(id: number) {
    this.router.navigate([`/management/industries/${id}`]);
  }

  isCategoryRoute(): boolean {
    const route = this.router.url;
    return route.includes('/categories');
  }

  isIndustryRoute(): boolean {
    const route = this.router.url;
    return route.includes('/industries');
  }

  isCategoryValid(): boolean {
    return !this.categoryList.some(
      (c) => c.name === this.category.name && c.id !== this.category.id
    );
  }

  isIndustryValid(): boolean {
    return !this.industryList.some(
      (i) => i.name === this.industry.name && i.id !== this.industry.id
    );
  }

  confirmDeleteCategory(id: number, name: string) {
    this.deleteMessage = `¿Está seguro de que desea eliminar la categoría?`;
    this.idToDelete = id;
  }

  confirmDeleteIndustry(id: number, name: string) {
    this.deleteMessage = `¿Está seguro de que desea eliminar el rubro?`;
    this.idToDelete = id;
  }

  deleteCategory() {
    if (!this.idToDelete) {
      return;
    }

    this.categoryService.deleteCategory(this.idToDelete).subscribe({
      next: (res) => {
        this.getCategories();
        console.log(this.categoryList);
        this.idToDelete = null;
        this.toastService.showSuccessToast(
          'Categoría eliminada correctamente!'
        );
      },
      error: (error) => {
        this.toastService.showErrorToast(
          'No se puede eliminar la categoría porque tiene productos asociados'
        );
      },
    });
  }

  deleteIndustry() {
    if (!this.idToDelete) {
      return;
    }

    this.industryService.deleteIndustry(this.idToDelete).subscribe({
      next: (res) => {
        this.getIndustries();
        this.idToDelete = null;
        this.toastService.showSuccessToast('Rubro eliminado correctamente!');
      },
      error: (error) => {
        this.toastService.showErrorToast(
          'No se puede eliminar el rubro porque tiene proveedores asociados'
        );
      },
    });
  }

  confirmRestoreCategory(id: number, name: string) {
    this.restoreMessage = `¿Está seguro de que desea restaurar la categoría?`;
    this.idToRestore = id;
  }

  confirmRestoreIndustry(id: number, name: string) {
    this.restoreMessage = `¿Está seguro de que desea restaurar el rubro?`;
    this.idToRestore = id;
  }

  restoreCategory() {
    if (this.idToRestore) {
      this.categoryService
        .restoreCategory(this.idToRestore)
        .subscribe((res) => {
          this.idToRestore = null;
          this.toastService.showSuccessToast(
            'Categoría restaurada correctamente!'
          );

          this.categoryService.getCategories().subscribe((res) => {
            this.categoryList = res;

            if (!this.hasDeletedCategories()) {
              this.deleteCategoryFilter = false;
            }
          });
        });
    }
  }

  restoreIndustry() {
    if (this.idToRestore) {
      this.industryService
        .restoreIndustry(this.idToRestore)
        .subscribe((res) => {
          this.idToRestore = null;
          this.toastService.showSuccessToast('Rubro restaurado correctamente!');

          this.industryService.getIndustries().subscribe((res) => {
            this.industryList = res;

            if (!this.hasDeletedIndustries()) {
              this.deleteIndustryFilter = false;
            }
          });
        });
    }
  }

  updateCategorySort() {
    switch (this.categorySort) {
      case 'Default':
        this.categorySort = 'Ascending';
        break;
      case 'Ascending':
        this.categorySort = 'Descending';
        break;
      default:
        this.categorySort = 'Default';
        break;
    }
  }

  updateIndustrySort() {
    switch (this.industrySort) {
      case 'Default':
        this.industrySort = 'Ascending';
        break;
      case 'Ascending':
        this.industrySort = 'Descending';
        break;
      default:
        this.industrySort = 'Default';
        break;
    }
  }

  onCategoryCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteCategoryFilter = true;
    } else {
      this.deleteCategoryFilter = false;
    }
  }

  onIndustryCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteIndustryFilter = true;
    } else {
      this.deleteIndustryFilter = false;
    }
  }

  hasDeletedCategories() {
    return this.categoryList.some((category) => category.isDeleted);
  }

  hasDeletedIndustries() {
    return this.industryList.some((industry) => industry.isDeleted);
  }
}
