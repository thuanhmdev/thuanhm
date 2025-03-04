import Avatar from '@/components/avatar';
import DateFormat from '@/components/date-format';
import ShareSocialMedia from '@/components/share-social-media';
import { sendRequest } from '@/http/http';
import { convertURL } from '@/utils/urlUtil';
import Image from 'next/image';
import Link from 'next/link';
import React from 'react';
import Comment from './comment';

const page = async ({ params }: any) => {
    const [blog, topBlogs] = await Promise.all([
        sendRequest<TResponse<TBlog>>({
            url: `/blog-api/blog/${params.slug?.[0]}`,
            method: "GET",
            nextOption: { cache: 'no-store' }
        }),
        sendRequest<TResponse<TBlog[]>>({
            url: `/blog-api/blog/related-list`,
            method: "GET",
            queryParams: {
                id: params.slug[0]
            },
            nextOption: { cache: 'no-store' }
        }),
    ]);

    return (
        <div className="container py-10 min-h-[100vh]">
            {blog.data ? (
                <>
                    <div className="grid grid-cols-4 relative gap-x-8">
                        <div className="col-span-3 xl:col-span-3">
                            <h1 className="text-2xl md:text-3xl  xl:text-4xl font-bold">
                                {blog.data.title}
                            </h1>
                            <div className="flex flex-wrap gap-y-1 items-center pt-5 justify-between mb-1">
                                <Avatar
                                    user={blog.data.blogger}
                                    classNameAvatar="w-[40px] h-[40px] xl:w-[48px] xl:h-[48px]"
                                    classNameTextAvatar="text-xs md:text-sm xl:text-base"
                                    hiddenBadge
                                />
                                <p className="text-[12px] block md:inline">
                                    Ngày đăng:{" "}
                                    <DateFormat
                                        date={blog.data.createdAt}
                                        classNameCustom="text-[10px] md:text-sm"
                                    />
                                </p>
                            </div>
                            <ShareSocialMedia title={blog.data.title} />
                            <div
                                className="my-4 ck ck-content"
                                dangerouslySetInnerHTML={{ __html: blog.data.content }}
                            ></div>

                            <div className="w-full h-[1px] bg-blue-500"></div>
                            <Comment blogData={blog.data} />
                        </div>
                        <div className="hidden xl:block xl:col-span-1">
                            <div className="sticky top-[80px]">
                                <h2 className="font-bold text-xl">BÀI VIẾT MỚI</h2>
                                <ul className="mt-4 space-y-2">
                                    {topBlogs?.data?.map((item) => (
                                        <li key={item.id} className="border-b-2 border-neutral-500-500">
                                            <Link
                                                href={`/bai-viet/${item.id}/${convertURL(item.title)}`}
                                                className="flex gap-4 cursor-pointer transition duration-300 hover:-translate-y-[2px]  hover:scale-[101%] hover:shadow-md"
                                            >
                                                <div className="py-2 flex flex-col gap-y-1.5 lg:gap-y-2.5">
                                                    <h2 className="md:text-base text-lg font-bold">
                                                        {item.title}
                                                    </h2>
                                                </div>
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                </>
            ) : (
                <>
                    <Image
                        className="mx-auto"
                        src="/images/no-result.gif"
                        height={400}
                        width={400}
                        alt="NotFound"
                    />
                    <p className="text-center">Xin lỗi bài viết này không còn tồn tại</p>
                </>
            )}
        </div>
    );
};


export default page;