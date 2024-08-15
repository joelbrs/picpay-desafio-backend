import setupRoutes from "@/main/config/routes";
import fastify from "fastify";

export const setupApp = async () => {
    const app = fastify({ logger: true });
    await setupRoutes(app);

    return app;
};
