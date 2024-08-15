import { Client } from "pg";

interface PgEnv {
    host: string;
    password: string;
    user: string;
    port: number;
    database: string;
}

export const PgHelper = {
    client: null as Client | null,
    connection: null as PgEnv | null,

    async connect(connection: PgEnv) {
        this.client = new Client(connection);
        this.connection = connection;

        await this.client.connect();
    },
};
