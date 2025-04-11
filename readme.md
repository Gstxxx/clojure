# RPS - Rock, paper, scissors

vou desenvolver o jogo pedra, papel e tesoura usando clj pra aprender a lang.

react no clojure é poggers

# Definição

- Jogadas (Moves)
    * Pedra (Rock)
    * Papel (Paper)
    * Tesoura (Scissors)

- Jogador

- Partida
    * Estado atual do jogo
    * Placar
    * Rodada atual
    * Historico de jogadas

- Resultado
    * Vitoria
    * Derrota
    * Empate

- Estatisticas
    * total de vitorias 
    * total de derrotas
    * total de empates
    * porcentagem de vitorias
    * sequencia atual

- Configurações
    * Número de rodadas
    * Modo de jogo (melhor de X, torneio, etc.)

- Keywords para as jogadas (:rock, :paper, :scissors)


## Definição técnica

- Backend em clojure (obviamente)
- Frontend em clojurescript

## Front

### Views

    * Tela inicial
    * Tela de jogo
    * Tela de resultados
    * Tela de estatísticas

### estrutura do projeto

```md

rps-game/
├── backend/
│   ├── src/
│   │   ├── rps/
│   │   │   ├── core.clj        # Lógica principal do jogo
│   │   │   ├── game.clj        # Estado e regras do jogo
│   │   │   ├── stats.clj       # Cálculos estatísticos
│   │   │   └── api.clj         # Endpoints e handlers
│   │   └── rps/
│   │       └── server.clj      # Configuração do servidor
│   └── test/                   # Testes do backend
├── frontend/
│   ├── src/
│   │   ├── rps/
│   │   │   ├── core.cljs      # Ponto de entrada
│   │   │   ├── components/    # Componentes UI
│   │   │   ├── state/         # Gerenciamento de estado
│   │   │   └── views/         # Telas do jogo
│   │   └── rps/
│   │       └── client.cljs    # Configuração do cliente
│   └── resources/             # Assets estáticos
└── project.clj                # Configuração do projeto
```

## Fluxo de Dados:

    - Usuário interage com a interface
    - Frontend envia requisição para o backend
    - Backend processa a jogada
    - Backend retorna resultado
    - Frontend atualiza a interface

# How to run

Executar o backend com `lein run` no diretório raiz
Compilar o frontend com `lein cljsbuild once`
Abrir o arquivo `index.html` no navegador

