A fazer: 
```

- implementar compare?????
- VERIFICAR CLONESSSS
- Rever nomenclatura
- Implementar recordes por atividade, bem como quem os conseguiu
- Implementar recordes por atividade, por utilizador (Feito apenas para Calorias)
- Criar um plano de treino a partir do número de vezes que quer que uma atividade se repita por semana e ter em conta os seus objetivos


model: 
- Melhorar toString, rever as calorias
- Colocar throws no model


view:
- lidar com as exceções todas
```


find . -name \*.class -type f -delete



DUVIDAS
```
Dúvida: No caso de os swithces da criação de atividades forem para default, é suposto devolvermos o quê? (estamos a criar um sprint sem parâmetros)
Resposta: *** VERIFICAR SE OPÇÃO É VÁLIDA E, CASO NÃO SEJA, CANCECLAR A CRIAÇÃO DA ATIVIDADE, ISTO PARA NÃO HAVER A CRIAÇÃO DE UMA ATIVIDADE DESNECESSÁRIA***

Dúvida: Atividade é agregação de utilizador, mas não sabemos se o utilizador tem de ser agragação de atividade
Resposta: *** PODE SER AGREGAÇÃO ***

Dúvida: é suposto evidenciar as calorias totais consumidas num dado salto no tempo?
Resposta: *** DEVOLVE VOID ***

Dúvida: é suposto ter mais algum tipo de recordes para alem das calorias? se sim, como fazer sem repetir várias vezes
Resposta: *** BASTA CALORIAS ***

Dúvida: temos muitos métodos no model que apresentam exatamente o mesmo início (this.xxxxx.get(codigo).xx). O strategy resolve isso? Ou não há forma de resolver este problema?
Resposta: *** USAR MAP DE PREDICATES E FUNCOES ***

Dúvida: no que é que uma atividade hard influencia? como calcular calorias numa atividade hard?
Resposta:
*** SÓ FUNCIONA PARA A CRIAÇÃO DO PLANO DE TREINO COM OBJETIVOS. NADA INFLUENCIA NAS CALORIAS ***

Dúvida: Devemos eliminar os construtores não usados?
Resposta: *** NÃO ***

Dúvida: A criação e adição de um objeto tem de estar no menu ou no model
Resposta: *** DEVE ESTAR NO MENU ***

Dúvida: Aonde colocar os throws?
Resposta: *** TRATAR COM HANDLERS NO "CONTROLLER SEMPRE QUE FOR PARA VALIDAR PARAMETROS " ***
```
