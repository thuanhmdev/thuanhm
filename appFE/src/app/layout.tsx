import { sendRequest } from "@/http/http";
import type { Metadata } from "next";
import { Montserrat } from "next/font/google";
import "rc-pagination/assets/index.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "./globals.css";
import NextAuthWrapper from "./next-auth-wrapper";

const montserrat = Montserrat({ subsets: ["latin"] });

export async function generateMetadata(): Promise<Metadata> {
  const { data, statusCode } = await sendRequest<TResponse<TSetting>>({
    url: `/blog-api/settings`,
    method: "GET",
  });
  if (statusCode === 200) {
    return {
      title: data.title,
      description: data.description,
      openGraph: {
        siteName: data.siteName,
        locale: "vi_VN",
      },
    };
  } else {
    return {
      title: "Trang chủ",
      description: "",
      openGraph: {
        locale: "vi_VN",
        siteName: "ThuanHM",
      },
    };
  }
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning={true}>
      <body id="root" className={montserrat.className}>
        <NextAuthWrapper>
          {children}
          <ToastContainer
            position="top-right"
            autoClose={1200}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
          />
        </NextAuthWrapper>
      </body>
    </html>
  );
}
