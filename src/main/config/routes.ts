import { FastifyInstance } from "fastify";
import { readdirSync } from "fs";
import { join } from "path";

export default async function registerRoutes(
    app: FastifyInstance
): Promise<void> {
    const routesPath = join(__dirname, "../routes");
    const routeFiles = readdirSync(routesPath);

    for (const file of routeFiles) {
        if (!file.endsWith(".map")) {
            const route = await import(`../routes/${file}`);
            route.default(app);
        }
    }
}
