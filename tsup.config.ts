import { defineConfig } from "tsup";

export default defineConfig({
    entry: ["src/main/server.ts"],
    format: ["cjs", "esm"],
    loader: {
        ".sql": "text",
    },
});
