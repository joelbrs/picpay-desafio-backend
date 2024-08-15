/**
 * For a detailed explanation regarding each configuration property, visit:
 * https://jestjs.io/docs/configuration
 */

import type { Config } from "jest";

const config: Config = {
    clearMocks: true,
    collectCoverage: true,
    collectCoverageFrom: ["<rootDir>/tests/**/*.ts"],
    coverageDirectory: "coverage",
    coverageProvider: "v8",
    roots: ["<rootDir>/tests"],
    transform: {
        ".+\\.ts$": "ts-jest",
    },
};

export default config;
