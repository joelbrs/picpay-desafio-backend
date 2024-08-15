import "dotenv/config";
import { z } from "zod";

const environment_schema = z.object({
    PORT: z.coerce.number().default(3000),
    DB_PASSWORD: z.string(),
    DB_USER: z.string(),
    DB_NAME: z.string(),
    DB_PORT: z.coerce.number().default(5432),
});

const _env = environment_schema.safeParse(process.env);

if (!_env.success) {
    console.log("Invalid environments variables: ", _env.error?.format());
    throw new Error("Invalid environments variables");
}

export const env = _env.data;
