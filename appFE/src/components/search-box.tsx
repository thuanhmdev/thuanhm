'use client'
import { Search } from "lucide-react";
import { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";

export default function SearchBox() {
    const router = useRouter();
    const searchParams = useSearchParams();
    const [keyword, setKeyword] = useState(searchParams.get("keyword") || "");

    const handleSearch = () => {
        const params = new URLSearchParams(searchParams);
        params.set("keyword", keyword);
        router.push(`?${params.toString()}`);
    };

    return (
        <div className="flex justify-end space-x-1">
            <input
                value={keyword}
                onChange={(e) => setKeyword(e.target.value)}
                className="w-1/5 rounded-lg px-3 py-1 border-2 border-gray-300 hover:border-gray-400 outline-1 outline-gray-400 transition-all duration-200 ease-in-out"
                placeholder="Tìm kiếm"
                onKeyDown={(e) => e.key === 'Enter' && handleSearch()}
            />
            <button
                onClick={handleSearch}
                className="bg-neutral-500 py-1 rounded-md text-white font-bold hover:bg-neutral-500/80 text-sm px-2"
            >
                <Search />
            </button>
        </div>
    );
}