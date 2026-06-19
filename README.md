# Focos Dengue

O Focos Dengue é um aplicativo Android desenvolvido para auxiliar a população no monitoramento colaborativo de focos de dengue e problemas de acessibilidade urbana.

## Guia do Desenvolvedor

#### 1. Arquitetura: Clean Architecture (Simplificada) + MVVM
#### 2. Linguagem de Programação: Kotlin
#### 3. UI Toolkit: Jetpack Compose
#### 4. Dependências:
  - **Ktor Client (Android):** Para requisições de rede e consumo de APIs de forma assíncrona.
  - **Supabase Kotlin SDK:** Utilizado como BaaS (Backend as a Service) para:
  - **Auth (GoTrue):** Autenticação e gerenciamento de usuários.
  - **Storage:** Armazenamento e upload de arquivos de mídia.
  - **Postgrest:** Integração e consultas diretas ao banco de dados PostgreSQL.
  - **Kotlinx Serialization & Datetime:** Para parsing de JSON e manipulação de datas/fusos horários.
  - **Google Play Services Maps:** Integração de mapas nativos do ecossistema Google.
  - **Avifkit:** Conversão de imagens para o formato `.avif`.
  - **Coil Compose:** Carregamento de imagens assíncronas otimizadas no Jetpack Compose.
  - **Navigation Compose:** Gerenciamento de rotas e navegação entre telas.

---

### Tecnologias Utilizadas

#### Este aplicativo foi desenvolvido utilizando o **[Android Studio](https://developer.android.com/studio)**.

#### Este projeto utiliza o **[Supabase](https://supabase.com/)** como BaaS (Backend as a Service), eliminando a necessidade de um servidor próprio.-

#### ⚙️ Configuração do Supabase

Para rodar este aplicativo localmente, você precisará conectar o projeto à sua própria instância do Supabase:

1. Crie uma conta gratuita em [supabase.com](https://supabase.com) e crie um novo projeto.
2. No painel do Supabase, vá em **Project Settings > API** e copie a `Project URL` e a `Publishable key`.
3. Na raiz do seu projeto, crie um arquivo com o nome `secrets.properties`.
4. Depois copie o conteúdo do arquivo `secrets.defaults.properties` e cole no arquivo `secrets.properties`.
5. Então copie as suas chaves para o arquivo `secrets.properties`.

####

---

## Licença e Créditos

Este software é um código aberto licenciado sob a [GNU General Public License v3.0 (GPLv3)](LICENSE). 

Qualquer pessoa pode modificar e distribuir versões deste aplicativo, desde que o código-fonte dessas modificações também seja obrigatoriamente liberado sob a licença GPLv3.

### Componentes de Terceiros
* **Ícones do Aplicativo**: Este projeto utiliza recursos visuais do [Google Fonts Icons](https://google.com) (Material Symbols), que estão licenciados sob a [Apache License 2.0](licenses/GOOGLE_ICONS_Apache-2.0.txt).
