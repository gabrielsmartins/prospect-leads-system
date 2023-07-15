import { Category } from "./category.model";

export class Product {

    id?: number;
    name?: string;
    category?: Category;
    active: boolean = true;
    createdAt?: Date;
    updatedAt?: Date;
    deletedAt?: Date;

}
