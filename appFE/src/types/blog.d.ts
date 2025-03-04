export {};
declare global {
  type TBlog = {
    id: number;
    title: string;
    content: string;
    image: string;
    description: string;
    keyword: string;
    blogger: TUser;
    category: TCategory;
    createdAt: number;
    updatedAt: number;
  };

  type TComment = {
    id: string;
    user: TUser;
    content: string | null;
    createdAt: number;
    blogId: string;
    rootId: string | null;
    answers: any;
  };

  type TCategory = {
    id: string;
    name: string;
    position: string;
    slug: string;
  };
}
