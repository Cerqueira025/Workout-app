A fazer: 
```

- ContainsKey -> putIfAbsent
- implementar compare?????
- VERIFICAR CLONESSSS
- Rever nomenclatura
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

Dúvida: temos muitos métodos no model que apresentam exatamente o mesmo início (this.xxxxx.get(codigo).xx). O strategy resolve isso? Ou não há forma de resolver este problema?
Resposta: *** USAR MAP DE PREDICATES E FUNCOES ***

Dúvida: no que é que uma atividade hard influencia? como calcular calorias numa atividade hard?
Resposta:
*** SÓ FUNCIONA PARA A CRIAÇÃO DO PLANO DE TREINO COM OBJETIVOS. NADA INFLUENCIA NAS CALORIAS ***

Dúvida: A criação e adição de um objeto tem de estar no menu ou no model
Resposta: *** DEVE ESTAR NO MENU ***

Dúvida: Aonde colocar os throws?
Resposta: *** TRATAR COM HANDLERS NO "CONTROLLER SEMPRE QUE FOR PARA VALIDAR PARAMETROS " ***
```


    res = new HashMap();
    for(veiculo v : veiculos.values()) {
        if(!res.containskey(v.getMarca())) 
            res.put(v.getMarca(), new ArrayList<Veiculo>());
        res.get(v.getMarca()).add(v);
        //res.put(v.getMarca(), res.get(v.getMarca()).add(v));
    }

    linha 317 - DriveItPL8 - Iterador Interno