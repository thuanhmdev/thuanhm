"use client";
import { sendRequest } from "@/http/http";
import Image from "next/image";
import React, { useEffect, useState } from "react";

const MessengerPlugin = () => {
  const [messenger, setMessenger] = useState("");

  const fanpageFacebookLink = async () => {
    const { data, status } = await sendRequest<TResponse<TSetting>>({
      url: `/blog-api/settings`,
      method: "GET",
    });
    if (status >= 200 && status <= 299) {
      setMessenger(data.messengerLink);
    }
  };

  useEffect(() => {
    fanpageFacebookLink();
  }, []);
  return (
    <>
      {messenger && (
        <a href={messenger} target="_blank" className="fixed right-5 bottom-10">
          <Image
            src={"/images/messenger.svg"}
            alt="avatar"
            width={40}
            height={40}
          />
        </a>
      )}
    </>
  );
};

export default MessengerPlugin;
