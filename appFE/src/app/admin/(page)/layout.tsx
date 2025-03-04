import { authOptions } from "@/app/api/auth/auth.option";
import Sidebar from "@/components/sidebar";
import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import AuthAdmin from "./auth-admin";

const AdminLayout = async ({ children }: { children: React.ReactNode }) => {
  const session = await getServerSession(authOptions);
  if (!session || session.user.role.name != "ADMIN") {
    redirect("/admin/login");
  }

  return (
    <>
      <AuthAdmin />
      <div className="relative flex min-h-screen w-screen bg-slate-50 gap-x-2">
        <div className="bg-[#2a3f54] text-white w-[180px] md:w-[200px] lg:w-[230px] xl:[250px] ">
          <Sidebar />
        </div>
        <div className="w-full overflow-x-hidden min-h-screen ps-4 py-2">{children}</div>
      </div>
    </>
  );
};

export default AdminLayout;
