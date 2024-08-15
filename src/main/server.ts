import { env } from "./config";
import { PgHelper } from "@/infra/database/postgres";

const DbConnection = {
    password: env.DB_PASSWORD,
    user: env.DB_USER,
    port: env.DB_PORT,
    database: env.DB_NAME,
};

PgHelper.connect(DbConnection)
    .then(async () => {
        const { setupApp } = await import("./config/app");
        const app = await setupApp();

        app.listen(
            {
                port: env.PORT,
                host: "127.0.0.1",
            },
            (err, address) => {
                if (err) {
                    return console.error(err);
                }
                console.log(`Server running at ${address}`);
            }
        );
    })
    .catch(console.error);
