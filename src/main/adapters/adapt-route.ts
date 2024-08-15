import { Controller } from "@/presentation/protocols";
import { FastifyRequest, FastifyReply } from "fastify";

export const adaptRoute = (controller: Controller) => {
    return async (request: FastifyRequest, reply: FastifyReply) => {
        const httpRequest = {
            ...(request.body || {}),
            ...(request.params || {}),
        };

        const httpResponse = await controller.handle(httpRequest);
        if (httpResponse.statusCode >= 200 && httpResponse.statusCode <= 299) {
            reply.code(httpResponse.statusCode).send(httpResponse.body);
        } else {
            reply.code(httpResponse.statusCode).send({
                error: httpResponse.body.message,
            });
        }
    };
};
