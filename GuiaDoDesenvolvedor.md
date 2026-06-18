# Guia do Desenvolvedor

## Visão Geral

O Focos Dengue é um aplicativo Android desenvolvido para auxiliar a população no monitoramento colaborativo de focos de dengue e problemas de acessibilidade urbana.

## Tecnologias Utilizadas
Kotlin
Android Studio
Jetpack Compose
Supabase Auth
Supabase Storage
Supabase PostgREST
Kotlin Serialization

## Arquitetura

O projeto utiliza Clean Architecture, dividida nas seguintes camadas:

### Domain
. Models

. Repository Interfaces

. Usecase

. Domain Services

### Data

. DTOs

. Mappers

. DataSources

. Repository Implementations

### UI
Screens

ViewModels

Componentes Compose

## Configuração do Ambiente
### Requisitos
Android Studio

Conta Supabase
### Execução
Clonar o repositório.

Abrir o projeto no Android Studio.

Configurar as chaves do Supabase.

Sincronizar o Gradle.

Executar o aplicativo.
## Banco de Dados
### Tabela reports

Armazena as denúncias registradas pelos usuários.

Campos principais:

. id

. location

. photo

. description

. type

. created_at

### Storage

#### Bucket:

. reports

Utilizado para armazenar as imagens das denúncias.

#### Fluxo de Cadastro de Denúncias

1 - O usuário seleciona uma imagem.

2 - A imagem é enviada para o Storage.

3 - É gerada uma URL pública.

4 - A denúncia é registrada na tabela reports.