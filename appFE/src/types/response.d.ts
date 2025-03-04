import { TUser } from "./user";

export {};

declare global {
  type TResponse<T> = {
    message: string | any;
    status: number;
    error?: string | null;
    data: T;
  };
  type TPagination<T> = {};

  type TPaginationResponse<T> = {
    message: string | any;
    status: number;
    error?: string | null;
    data: {
      page: number;
      size: number;
      totalPages: number;
      totalElements: number;
      data: T;
    };
  };
}
