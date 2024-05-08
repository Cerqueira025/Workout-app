MODEL:
```
- Melhorar toString, rever as calorias e bpm
- Colocar throws no model
```


DELEGATE:
```
- Questionar data da aplicação
- tratamento de parametros no controller com handlers.
- criarAtividade(), na view, não pode devolver Atividade. Dever ser criada uma atividade e inserida logo de seguida, mesmo que o código se repita
- implementar salto no tempo
- implementar recordes gerais e de cada um
- implementar criação de plano de treino com objetivos
- implementar queries (ter em atenção as mudanças feitas pelos MAP de Predicates e ToDoubleFunctions)
- implementar leitura e escrita em ficheiro
- lidar com exceções vindas do model e fazer alguns controlos de erros próprios (parâmetros, ...)
```


A FAZER NO FINAL:
```
- ver métodos para colocar em private e, par tal, retirar os throws caso não façam sentido
- CTRL+F de throws no Delegate (não podem existir)
- retirar métodos não usados com o INTELIJ
- Rever nomenclatura
- VERIFICAR CLONESSSS (Manter agragação em tudo?)
- corrigir ou retirar métodos do model que criam objetos de classes criadas (new xxxx(.....))
```


find . -name \*.class -type f -delete
