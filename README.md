A fazer: 
```
- Rever nomenclatura
- Implementar recordes por atividade, bem como quem os conseguiu
- Implementar recordes por atividade, por utilizador
- Criar um plano de treino a partir do número de vezes que quer que uma atividade se repita por semana e ter em conta os seus objetivos


model: 
- Melhorar toString, rever as calorias
- Colocar throws no model
- Métodos de criação de objetos (Atividade, Utilizador, PlanoDeTreino, ...(não sei se há mais)). Ver switch case de cada um.


controller:
- Rever dateTime do controller


view:
- lidar com as exceções todas
- Melhorar a forma como se lê o género
- ter verificações ao clicar em certas opções (Listas vazias, ...)
- fazer switch para distinguir diferentes criações
```

    1. implementar testes para todas a classes
    2. restruturar o model/view para usar o desgin pattern do delegate



DUVIDAS
```
Dúvida: Atividade é agregação de utilizador, mas não sabemos se o utilizador tem de ser agragação de atividade
Reposta: 

Dúvida: temos muitos métodos no model que apresentam exatamente o mesmo início (this.xxxxx.get(codigo).xx). O strategy resolve isso? Ou não há forma de resolver este problema?
Resposta:

Dúvida: Devemos eliminar os construtores não usados?
Resposta:

```
