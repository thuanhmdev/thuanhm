"use client";
import Image from "next/image";
import React from "react";

import Slider, { Settings } from "react-slick";
import { ChevronLeft, ChevronRight } from "lucide-react";
import RevealEffect from "../effect/reveal-effect";

const PrevArrow = (props: any) => {
  const { onClick } = props;
  return (
    <button
      className="bg-blue-500/50 p-1 rounded-md absolute left-1 top-[50%] -translate-y-[50%] z-10"
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
      className="bg-blue-500/50 p-1 rounded-md absolute right-1 top-[50%] -translate-y-[50%] z-10"
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
          Work Experience
        </h2>
        <div className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg space-y-10">
          <div className="border-l-[2px] border-neutral-800 ps-4 lg:ps-10 space-y-4">
            <h5 className="font-bold">
              🚧 Aug 2024 - Present | Freelancer - Web Developer
            </h5>
            <div>
              <RevealEffect>
                <div className="bg-black p-10 rounded-xl space-y-4 lg:items-center">
                  <div>
                    <h6>
                      <span className="text-neutral-100 text-base xl:text-xl font-bold">
                        Tuyen&apos;s Skinlab
                      </span>
                    </h6>

                    <ul>
                      <li>
                        - A website sharing beauty techniques, beauty secrets, and beauty journey through articles.
                      </li>
                      <li>
                        - Technologies currently being used to develop the project: React, Spring Boot, Typescript, Java, MySQL, Elasticsearch, WebAPI.
                      </li>
                      <li>
                        - Develop user interfaces and features for both users and administrators.
                      </li>
                      <li>
                        - Implement authentication, authorization users using JWT via access token, refresh token.
                      </li>
                      <li>
                        - Implement login with Google OAuth2.
                      </li>
                      <li>
                        - Develop functionality to notify users via email when a new comment is added to a post.
                      </li>
                      <li>
                        - Testing and deployment to a server with VPS (Ubuntu 22), Docker, Nginx.
                      </li>
                    </ul>
                  </div>
                </div>
              </RevealEffect>

              <RevealEffect>
                <div className="bg-black p-10 rounded-xl space-y-4 lg:items-center">
                  <div>
                    <h6>
                      <span className="text-neutral-100 text-base xl:text-xl font-bold">
                        ThuanHM&apos;s Blog
                      </span>{" "}
                      <a className="text-blue-500" href="https://thuanhm.com">
                        (thuanhm.com)
                      </a>
                    </h6>

                    <ul>
                      <li>
                        - A website that shares articles related to the field of information technology.
                      </li>
                      <li>
                        - Technologies currently being used to develop the project: NextJS/ReactJS, Spring Boot, Typescript, Java, MySQL, WebAPI.
                      </li>
                      <li>
                        - Develop user interfaces and features for both users and administrators.
                      </li>
                      <li>
                        - Implement authentication, authorization users using JWT via access token, refresh token.
                      </li>
                      <li>
                        - Implement login with Google, Github OAuth2.
                      </li>
                      <li>
                        - Develop functionality to notify users via email when a new comment is added to a post.
                      </li>
                      <li>
                        - Testing and deployment to a server with VPS (Ubuntu 22), Docker, Nginx.
                      </li>
                    </ul>
                  </div>
                </div>
              </RevealEffect>
            </div>
          </div>
        </div>
      </div >
    </section >
  );
};

export default PersonalProjectSection;
