import Image from "next/image";
import React from "react";
import TypingEffect from "../effect/typing-effect";

const HomeSection = () => {
  return (
    <section id="home" className="pt-12 md:pt-16 xl:pt-16 2xl:pt-16 3xl:pt-20">
      <div className="container ">
        <div className=" flex flex-col-reverse md:flex-row md:items-center md:justify-between gap-2 md:gap-10">
          <div className="space-y-3.5 md:space-y-5 lg:space-y-8">
            <h1 className="invisible">HA MINH THUAN</h1>
            <TypingEffect
              text="HA MINH THUAN"
              className="text-white text-3xl 2md:text-4xl lg:text-[42px] xl:text-5xl 2xl:text-6xl 3xl:text-[65px] font-extrabold"
            />

            <p className="text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg font-semibold">
              I&apos;m Web Developer. I love buiding beatiful interface, web
              apps, and everything between!
            </p>
            <div className="-ml-1 flex items-center gap-x-1">
              <Image
                src={"/images/mail.svg"}
                alt="mail"
                width={30}
                height={30}
              />
              <a
                href="mailto:thuanhmdev@gmail.com"
                className="text-white text-xs sm:text-sm  lg:text-base"
              >
                thuanhmdev@gmail.com
              </a>
            </div>

            <ul className="flex items-center text-white gap-x-4">
              <li>
                <a
                  className="flex items-center gap-x-2 border-[1px] border-blue-600 rounded-md px-2 py-1 group hover:scale-105 transition-all duration-100"
                  href="https://viblo.asia/u/thuanhm"
                  target="_blank"
                >
                  <Image
                    src={"/images/viblo.png"}
                    alt="mail"
                    width={14}
                    height={14}
                    style={{ width: "auto", height: "auto" }}
                    className="w-[14px] h-[14px]"
                  />
                  <p className="text-neutral-400 text-xs sm:text-sm  lg:text-sm group-hover:text-neutral-300">
                    Viblo
                  </p>
                </a>
              </li>
              <li>
                <a
                  className="flex items-center gap-x-2 border-[1px] border-blue-600 rounded-md px-2 py-1 group hover:scale-105 transition-all duration-100"
                  href="https://www.facebook.com/hmthuan99"
                  target="_blank"
                >
                  <Image
                    src={"/images/facebook.svg"}
                    alt="mail"
                    width={15}
                    height={15}
                  />
                  <p className="text-neutral-400 text-xs sm:text-sm  lg:text-sm group-hover:text-neutral-300">
                    Facebook
                  </p>
                </a>
              </li>
              <li>
                <a
                  className="flex items-center gap-x-2 border-[1px] border-blue-600 rounded-md px-2 py-1 group hover:scale-105 transition-all duration-100"
                  href="https://github.com/thuanhmdev"
                  target="_blank"
                >
                  <Image
                    src={"/images/github.svg"}
                    alt="mail"
                    width={15}
                    height={15}
                  />
                  <p className="text-neutral-400 text-xs sm:text-sm  lg:text-sm group-hover:text-neutral-300">
                    Github
                  </p>
                </a>
              </li>
            </ul>
          </div>
          <div>
            <div className="rounded-full block w-fit border-2 border-neutral-700 mx-auto">
              <div className="m-4 md:m-6 lg:m-8 2xl:m-10 relative size-[180px] sm:size-[200px]  md:size-[220px] 2md:size-[250px] lg:size-[300px] xl:size-[350px] 2xl:size-[400px] 3xl:size-[500px] rounded-full border-2 border-neutral-700 overflow-hidden">
                <Image
                  src={"/images/avatar.jpg"}
                  fill
                  sizes="20vm"
                  className="object-cover"
                  alt="avatar"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default HomeSection;
