"use client";
import Avatar from "@/components/avatar";
import { sendRequest } from "@/http/http";
import { signIn, signOut, useSession } from "next-auth/react";
import Image from "next/image";

import { useEffect, useState } from "react";
import { toast } from "react-toastify";

const Comment = ({ blogData }: { blogData: TBlog }) => {
  // signOut();
  const [comments, setComments] = useState<TComment[]>([]);
  const [writeComment, setWriteComment] = useState("");
  const [writeAnswerComment, setWriteAnswerComment] = useState({
    content: "",
    rootId: "",
    replyUserId: "",
  });
  const { data: session } = useSession();

  const handleGetComments = async () => {
    if (!blogData.id) return;
    const { status, data } = await sendRequest<TResponse<TComment[]>>({
      url: `/blog-api/comment/${blogData.id}`,
      method: "GET",
      typeComponent: "CSR",
    });

    if (status >= 200 && status <= 299) {
      const listParent = data.filter((i: TComment) => !i.rootId);
      const finalList = listParent.map((item: TComment) => {
        const listChilren = data.filter(
          (itemFilter) => itemFilter.rootId === item.id
        );
        item.answers = listChilren.sort((a, b) => a.createdAt - b.createdAt);
        return item;
      });

      setComments(finalList);
    }
  };

  useEffect(() => {
    handleGetComments();
  }, [blogData]);

  const handleWriteComment = async () => {
    if (!writeComment) return;

    const { status, data, message } = await sendRequest<TResponse<TComment>>({
      url: `/blog-api/comment/create`,
      method: "POST",
      body: {
        content: writeComment,
        blogId: blogData.id,
        userId: session?.user.id,
        rootId: "",
        replyUserId: blogData.blogger.id,
        blogUrl: (window as Window).location.href,
      },
      headers: {
        Authorization: `Bearer ${session?.accessToken}`,
      },
      typeComponent: "CSR",
    });
    if (status >= 200 && status <= 299) {
      toast.success("Gửi bình luận thành công");
      handleGetComments();
      setWriteComment("");
      setWriteAnswerComment({
        content: "",
        rootId: "",
        replyUserId: "",
      });
    }
    else {
      toast.error(message);
    }
  };

  const handleWriteAnswerComment = async () => {
    if (!writeAnswerComment.content) return;

    const { status, data } = await sendRequest<TResponse<TComment[]>>({
      url: `/blog-api/comment/create`,
      method: "POST",
      body: {
        content: writeAnswerComment.content,
        blogId: blogData.id,
        userId: session?.user.id,
        rootId: writeAnswerComment.rootId ?? "",
        replyUserId: writeAnswerComment.replyUserId,
        blogUrl: (window as Window).location.href,
      },
      typeComponent: "CSR",
    });
    if (status >= 200 && status <= 299) {
      toast.success("Gửi bình luận thành công");
      handleGetComments();
      setWriteComment("");
      setWriteAnswerComment({
        content: "",
        rootId: "",
        replyUserId: "",
      });
    }
  };
  const handleSignOut = () => {
    signOut({ callbackUrl: "" });
    sendRequest<TResponse<TBlog[]>>({
      url: `/blog-api/auth/logout`,
      method: "GET",
      headers: {
        Authorization: `Bearer ${session?.accessToken}`,
      },
      typeComponent: "CSR",
    });
  };

  return (
    <>
      <div className="pt-3 bg-white rounded space-y-2">
        <h2 className="text-xl font-bold">Bình luận</h2>
        {!session && (
          <div className="w-full py-8 flex flex-col justify-center items-center bg-slate-50">
            <i className="text-center py-2">
              Đăng nhập để thảo luận bài viết này
            </i>
            <ul className="flex flex-wrap items-center gap-4 m-0 text-lg lg:text-xl xl:text-2xl">
              <li>
                <div
                  className="cursor-pointer"
                  onClick={() =>
                    signIn("google", { callbackUrl: `/bai-viet/${blogData.id}` })
                  }
                >
                  <Image
                    width={25}
                    height={25}
                    src={"/images/google.svg"}
                    alt="facebook"
                    className="hover:scale-[1.1] transition-all ease-in-out duration-250"
                  />
                </div>
              </li>
              <li>
                <div
                  className="cursor-pointer"
                  onClick={() =>
                    signIn("github", { callbackUrl: `/bai-viet/${blogData.id}` })
                  }
                >
                  <Image
                    width={25}
                    height={25}
                    src={"/images/github.svg"}
                    alt="github"
                    className="hover:scale-[1.1] transition-all ease-in-out duration-250"
                  />
                </div>
              </li>
            </ul>
          </div>
        )}

        {session && (
          <div>
            <div className="flex items-center justify-between gap-x-2 py-2">
              <Avatar user={session.user} />
              <button
                onClick={() => handleSignOut()}
                className="text-sm text-red-400 border-[1px] px-2 rounded-md hover:text-white hover:bg-red-400 transition-all duration-100"
              >
                Đăng xuất
              </button>
            </div>
            <textarea
              className="border focus:outline-blue-200 p-2 rounded w-full"
              placeholder="Bình luận về bài viết..."
              value={writeComment}
              onChange={(e) => setWriteComment(e.target.value)}
            ></textarea>

            <div>
              <button
                onClick={handleWriteComment}
                className="px-4 py-1.5 bg-blue-500 text-white rounded font-light hover:bg-blue-700"
              >
                Gửi
              </button>
            </div>
          </div>
        )}

        <hr></hr>
        <div>
          {comments.map((item: TComment) => (
            <div key={item.id} className="py-4">
              <Avatar date={item.createdAt} user={item.user} />

              <div className="ml-[55px] ">
                <p className="bg-gray-50 p-2 ">{item.content}</p>
              </div>
              <div className="ml-[55px] ">
                {session && (
                  <button
                    type="button"
                    className="text-[14px]  text-sky-500 mt-1"
                    onClick={() =>
                      setWriteAnswerComment((prev) => ({
                        ...prev,
                        rootId: item.id,
                        userId: session.user.id,
                        content: `(@${item.user.name}): `,
                        replyUserId: item.user.id
                        ,
                      }))
                    }
                  >
                    Trả lời
                  </button>
                )}
              </div>
              {item.answers.map((item2: TComment) => (
                <div key={item2.id} className="ml-[55px]">
                  <Avatar user={item2.user} date={item2.createdAt} />
                  <div className="ml-[55px] ">
                    <p className="bg-gray-50 p-2 ">{item2.content}</p>
                  </div>
                  <div className="ml-[55px] ">
                    {session && (
                      <button
                        type="button"
                        className="text-[14px]  text-sky-500 mt-1"
                        onClick={() =>
                          setWriteAnswerComment((prev) => ({
                            ...prev,
                            rootId: item.id,
                            userId: session.user.id,
                            content: `(@${item2.user.name}): `,
                            replyUserId: item2.user.id,

                          }))
                        }
                      >
                        Trả lời
                      </button>
                    )}
                  </div>
                </div>
              ))}

              {session && writeAnswerComment.rootId === item.id && (
                <div className="ml-[55px] ">
                  <Avatar user={session.user} />
                  <textarea
                    className="border focus:outline-blue-200 p-2 rounded w-full"
                    placeholder="Trả lời bình luận này..."
                    value={writeAnswerComment.content}
                    onChange={(e) =>
                      setWriteAnswerComment((prev) => ({
                        ...prev,
                        content: e.target.value,
                      }))
                    }
                  ></textarea>

                  <div>
                    <button
                      className="px-4 py-1.5 bg-blue-500 text-white rounded font-light hover:bg-blue-700"
                      onClick={handleWriteAnswerComment}
                    >
                      Gửi
                    </button>
                  </div>
                </div>
              )}
            </div>
          ))}
        </div>
      </div >
    </>
  );
};

export default Comment;
