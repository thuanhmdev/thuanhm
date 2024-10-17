"use client";
import Image from "next/image";
import React from "react";

import Slider, { Settings } from "react-slick";
import { ChevronLeft, ChevronRight } from "lucide-react";

const PrevArrow = (props: any) => {
  const { onClick } = props;
  return (
    <button
      className="bg-blue-500/50 p-1 rounded-md absolute left-1 top-[150px] z-10"
      onClick={onClick}
    >
      <ChevronLeft className="text-neutral-50" />
    </button>
  );
};

const NextArrow = (props: any) => {
  const { onClick } = props;
  return (
    <button
      className="bg-blue-500/50 p-1 rounded-md absolute right-1 top-[150px]"
      onClick={onClick}
    >
      <ChevronRight className="text-neutral-50" />
    </button>
  );
};

const PersonalProjectSection = () => {
  const settings: Settings = {
    dots: false,
    infinite: true,
    // fade: true,
    speed: 1000,
    autoplaySpeed: 6000,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
  };
  return (
    <section id="project" className="pt-16 xl:pt-32 ">
      <div className="container space-y-10 ">
        <h2 className="text-white text-xl sm:text-2xl 2md:text-3xl lg:text-4xl 2xl:text-[42px] 3xl:text-5xl font-semibold">
          Personal Project
        </h2>
        <div className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg">
          <div className="border-l-[2px] border-neutral-800 ps-4 lg:ps-10 space-y-10">
            <Slider {...settings}>
              <div>
                <div className="flex flex-col lg:flex-row gap-x-10 bg-black p-10 rounded-xl space-y-4 lg:items-center">
                  <div className="child relative group-hover:scale(1.2) w-[300px] h-[200px] lg:w-[400px] lg:h-[300px] 2xl:w-[500px] 2xl:h-[400px]   rounded-2xl overflow-hidden">
                    <Image
                      src={"/images/thuanhm.png"}
                      alt="thuanhm"
                      sizes="20vw"
                      fill
                      className="object-contain"
                    />
                  </div>
                  <div>
                    <h6>
                      <span className="text-neutral-100 text-base xl:text-xl font-bold">
                        HA MINH THUAN
                      </span>{" "}
                      <a className="text-blue-500" href="https://thuanhm.com">
                        (thuanhm.com)
                      </a>
                    </h6>

                    <p>
                      <span className="text-neutral-200">Description:</span> My
                      personal website containing a portfolio and blog.
                    </p>

                    <div>
                      <p className="text-neutral-200">Technologies:</p>
                      <ul className="border-l-2 border-neutral-800 pl-3">
                        <li>
                          <span className="text-neutral-200">Frontend:</span>{" "}
                          ReactJS, NextJS, Typescript, NextAuth
                        </li>
                        <li>
                          <span className="text-neutral-200">Backend:</span>{" "}
                          Java, Spring Framework (Spring Boot, Spring security, ...), MySQL
                        </li>
                      </ul>
                    </div>

                    <p>
                      <span className="text-neutral-200">Link sources:</span>{" "}
                      <a
                        className="text-blue-500"
                        href="https://github.com/thuanhmdev/thuanhm"
                      >
                        https://github.com/thuanhmdev/thuanhm/
                      </a>
                    </p>
                  </div>
                </div>
              </div>
              <div>
                <div className="flex flex-col lg:flex-row gap-x-10 bg-black p-10 rounded-xl space-y-4 lg:items-center">
                  <div className="child relative group-hover:scale(1.2) w-[300px] h-[200px] lg:w-[400px] lg:h-[300px] 2xl:w-[500px] 2xl:h-[400px]   rounded-2xl overflow-hidden">
                    <Image
                      src={"/images/skinlabbytuyen.png"}
                      alt="skinlabbytuyen"
                      sizes="20vw"
                      fill
                      className="object-contain"
                    />
                  </div>
                  <div>
                    <h6>
                      <span className="text-neutral-100 text-base xl:text-xl font-bold">
                        Skinlab by Tuyen
                      </span>{" "}
                      <a
                        className="text-blue-500"
                        href="https://skinlabbytuyen.io.vn"
                      >
                        (skinlabbytuyen.io.vn)
                      </a>
                    </h6>

                    <p>
                      <span className="text-neutral-200">Description:</span>{" "}
                      Blog website sharing knowledge and beauty experiences.
                    </p>

                    <div>
                      <p className="text-neutral-200">Technologies:</p>
                      <ul className="border-l-2 border-neutral-800 pl-3">
                        <li>
                          <span className="text-neutral-200">Frontend:</span>{" "}
                          ReactJS, NextJS, Typescript, NextAuth
                        </li>
                        <li>
                          <span className="text-neutral-200">Backend:</span>{" "}
                          Java, Spring Framework (Spring Boot, Spring security, ...), MySQL
                        </li>
                        <li>
                          <span className="text-neutral-200">Deploy:</span> VPS
                          Linux (Ubuntu 22), Docker, Nginx
                        </li>
                      </ul>
                    </div>

                    <p>
                      <span className="text-neutral-200">Link sources:</span>{" "}
                      <a
                        className="text-blue-500"
                        href="https://github.com/thuanhmdev/skinlab-by-tuyen"
                      >
                        https://github.com/thuanhmdev/skinlab-by-tuyen/
                      </a>
                    </p>
                  </div>
                </div>
              </div>
            </Slider>
          </div>
        </div>
      </div>
    </section>
  );
};

export default PersonalProjectSection;
