MODEL:
```
- Colocar throws no model (em processo...)
- Criar métodos para não deixar o Delegate usar métodos externos ao Model ****************
```


DELEGATE:
```
- colocar data na aplicação (Feito - por verificar com salto no tempo)
- criarAtividade(), na view, não pode devolver Atividade. Dever ser criada uma atividade e inserida logo de seguida, mesmo que o código se repita (Feito - por verificar pelo professor)
- implementar salto no tempo

- implementar recordes gerais e de cada um
- implementar criação de plano de treino com objetivos
- implementar queries (ter em atenção as mudanças feitas pelos MAP de Predicates e ToDoubleFunctions)
- implementar leitura e escrita em ficheiro
- lidar com exceções vindas do model e fazer tratamento de parametros no controller com handlers
```


A FAZER NO FINAL:
```
- ver métodos para colocar em private e, par tal, retirar os throws caso não façam sentido
- CTRL+F de throws no Delegate (não podem existir)
- retirar métodos não usados com o INTELIJ
- Rever nomenclatura
- VERIFICAR CLONESSSS (Manter agragação em tudo?)
- corrigir ou retirar métodos do model que criam objetos de classes criadas (new xxxx(.....))
- Melhorar toString, rever as calorias e bpm
- mudar nome exceções
- ver que Exceptions.java não se usam
```


find . -name \*.class -type f -delete
