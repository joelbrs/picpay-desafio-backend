import { Middleware } from "@/presentation/protocols";
import { FastifyReply, FastifyRequest } from "fastify";

export const adaptMiddleware = (middleware: Middleware) => {
    return async (request: FastifyRequest, reply: FastifyReply) => {
        const httpRequest = {
            ...(request.body || {}),
        };

        const httpResponse = await middleware.handle(httpRequest);

        if (httpResponse.statusCode === 403) {
            reply.status(403).send({ error: httpResponse.body.message });
        }
    };
};
