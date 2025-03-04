import Image from 'next/image';
import React from 'react';

const FollowMe = () => {
    return (
        <div className="sticky top-[150px] space-y-4">
            <h2 className="font-bold text-xl">Theo dõi tôi tại</h2>
            <ul className="flex flex-wrap items-center text-white gap-4">
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
                        <p className="text-neutral-400 text-xs sm:text-sm  lg:text-sm ">
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
                        <p className="text-neutral-400 text-xs sm:text-sm  lg:text-sm ">
                            Facebook
                        </p>
                    </a>
                </li>
            </ul>
        </div>
    );
};



export default FollowMe;