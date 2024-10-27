"use client";
import Image from "next/image";
import Link from "next/link";
import { AlignJustify, SquareX } from "lucide-react";
import { useState } from "react";
import { usePathname } from "next/navigation";

const Header = () => {
  const pathname = usePathname();
  const isBlogPage = pathname.startsWith("/blog");

  const [showMenuMobile, setShowMenuMobile] = useState(false);
  const handleToggleMenuMobile = () => {
    setShowMenuMobile((prev: boolean) => !prev);
  };

  const hItems = [
    { id: "home", title: "Home", url: `/#home` },
    { id: "about", title: "About", url: `/#about` },
    { id: "project", title: "Personal Project", url: `/#project` },
    { id: "blog", title: "Blog", url: `/blog` },
  ];

  return (
    <>
      <header
        className={`sticky top-0 z-[11]  shadow-sm shadow-neutral-500 py-2.5 ${isBlogPage ? "bg-white text-black" : "bg-background text-neutral-200"
          }`}
      >
        <div className="container flex justify-between items-center h-full">
          <Link href="/" className="relative">
            <div className="h-[60px] w-[150px] relative">
              <Image
                className="object-contain object-left"
                alt="Logo"
                src={"/images/logo.png"}
                fill
                sizes="150px"
                priority
                quality={100}
              />
            </div>
          </Link>
          <ul className="hidden md:flex flex-wrap items-center md:gap-8 lg:gap-12 xl:gap-16 m-0  font-semibold text-base">
            {hItems.map((item) => (
              <li key={item.id} className="hover:opacity-80">
                <a href={`${item.url}`}>{item.title}</a>
              </li>
            ))}
          </ul>
          <div
            onClick={handleToggleMenuMobile}
            className="block md:hidden text-white cursor-pointer hover:opacity-80"
          >
            {showMenuMobile ? <SquareX /> : <AlignJustify />}
          </div>
        </div>

        <div
          className={`fixed ${showMenuMobile ? "h-screen w-full pt-1" : "invisible h-0 w-full pt-0"
            }  md:hidden bg-[#13161c] transition-all duration-500 ease-in-out `}
        >
          <div className="container">
            <ul
              className={`${showMenuMobile ? "visible" : "invisible"
                } flex flex-col text-white gap-y-3 transition-all ease-in-out`}
            >
              {hItems.map((item) => (
                <li
                  key={item.id}
                  className="hover:opacity-80 px-2 py-1 rounded-md cursor-pointer"
                >
                  <a href={`${item.url}`}>{item.title}</a>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </header>
    </>
  );
};

export default Header;
