"use client";
import React, { useEffect, useState } from "react";
import {
  FacebookIcon,
  FacebookMessengerIcon,
  FacebookMessengerShareButton,
  FacebookShareButton,
  TelegramIcon,
  TelegramShareButton,
  TwitterIcon,
  TwitterShareButton,
} from "next-share";

interface Iprops {
  title: string;
}
const ShareSocialMedia = ({ title }: Iprops) => {
  const [url, setUrl] = useState("");
  useEffect(() => {
    setUrl((window as Window).location.href);
  }, []);

  return (
    <div className="space-y-1">
      <p className="text-sm">Chia sẻ:</p>
      <div className="flex items-center  gap-x-2">
        <FacebookShareButton url={url} quote={title}>
          <FacebookIcon size={24} round />
        </FacebookShareButton>
        <FacebookMessengerShareButton url={url} appId={"526164413408334"}>
          <FacebookMessengerIcon size={24} round />
        </FacebookMessengerShareButton>
      </div>
    </div>
  );
};

ShareSocialMedia.propTypes = {};

export default ShareSocialMedia;
