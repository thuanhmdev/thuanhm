"use client";
import Image from "next/image";
import Link from "next/link";
import { AlignJustify, SquareX } from "lucide-react";
import React, { useEffect, useState } from "react";
import { usePathname } from "next/navigation";
import { sendRequest } from "@/http/http";

const Header = () => {
  const [showMenuMobile, setShowMenuMobile] = useState(false);
  const [categories, setCategories] = useState<TCategory[]>();
  const pathname = usePathname();
  const isProfilePage = pathname.startsWith("/my-profile");

  const [classNameProfilePage, setClassNameProfilePage] = useState(() => {
    return isProfilePage ? "bg-background text-neutral-200" : "bg-white text-black"
  })

  useEffect(() => {
    setClassNameProfilePage(() => isProfilePage ? "bg-background text-neutral-200" : "bg-white text-black")
  }, [pathname])


  const handleToggleMenuMobile = () => {
    setShowMenuMobile((prev: boolean) => !prev);
  };


  const handleFetchCategories = async () => {
    let url = `/blog-api/category/list`;
    const result = await sendRequest<TResponse<TCategory[]>>({
      url: url,
      method: "GET",
      typeComponent: "CSR",
    });
    if (result.status >= 200 && result.status <= 299) {
      setCategories(result.data);
    }
  };

  useEffect(() => {
    handleFetchCategories()
  }, []);


  if (pathname.startsWith("/admin")) {
    return <></>
  }

  return (
    <>
      <div className={classNameProfilePage}>
        <Link href="/" >
          <div className="h-[60px] w-[150px] relative mx-auto">
            <Image
              className="object-contain object-center"
              alt="Logo"
              src={"/images/logo.png"}
              fill
              sizes="150px"
              priority
              quality={100}
            />
          </div>
        </Link>
      </div >
      <div className={`sticky top-0 z-[11] py-2.5 ${classNameProfilePage} h-full gap-y-2 border-b-[1px] border-b-neutral-400`}>
        <ul className="hidden md:flex flex-wrap justify-center items-center md:gap-8 lg:gap-12 xl:gap-16 m-0  font-semibold text-base py-2">
          <li>
            <Link href="/">Trang chủ</Link>
          </li>
          {
            (Array.isArray(categories) && categories?.length > 0) && categories?.map(item =>
              <li key={item.id}>
                <Link href={`/danh-muc/${item.slug}`}>{item.name}</Link>
              </li>)
          }

        </ul>
        <div
          onClick={handleToggleMenuMobile}
          className={`flex md:hidden ${classNameProfilePage} justify-center cursor-pointer hover:opacity-80`}
        >
          {showMenuMobile ? <SquareX /> : <AlignJustify />}
        </div>
      </div>

      <div
        className={`fixed ${showMenuMobile ? "h-screen w-full pt-1 z-[12]" : "invisible h-0 w-full pt-0"
          }  md:hidden ${classNameProfilePage} transition-all duration-500 ease-in-out `}
      >
        <div className="container">
          <ul
            className={`${showMenuMobile ? "visible" : "invisible"
              } flex flex-col gap-y-3 transition-all ease-in-out`}
          >
            <li>
              <Link href="/">Trang chủ</Link>
            </li>
            {
              (Array.isArray(categories) && categories?.length > 0) && categories?.map(item =>
                <li key={item.id}>
                  <Link href={`/danh-muc/${item.slug}`}>{item.name}</Link>
                </li>)
            }
            <li>
              <Link href="/my-profile">My Profile</Link>
            </li>
          </ul>
        </div>
      </div>

    </>
  );
};

export default Header;
