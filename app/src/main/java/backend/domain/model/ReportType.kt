package backend.domain.model
enum class ReportType {
    BOTTLE_DRUMS_UNCOVERED,          // Caixa D'água destampada
    TIRES_DISCARDED,                   // Pneus Abandonados
    BOTTLES_OR_CONTAINERS_WITH_WATER,  // Garrafas/Recipientes descartados com água
    CONSTRUCTION_DEBRIS,               // Entulho de construção
    POOL_UNDER_MINV,                    // piscina sem manutenção
    OTHER                                // "outro"
}