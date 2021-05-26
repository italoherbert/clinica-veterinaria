
insert into pessoa ( id, nome, telefone, email ) values ( 1, 'Ítalo', '(00) 12345-6789', 'italo@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 2, 'Kelly', '(00) 99823-0987', 'kelly@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 3, 'João', '(00) 33902-9908', 'joao@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 4, 'Maria', '(00) 44875-0987', 'maria@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 5, 'Pedro', '(00) 09879-3847', 'pedro@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 6, 'Tiago', '(00) 90384-9587', 'tiago@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 7, 'Felipe', '(00) 87495-0498', 'felipe@sociedadefeliz.com.br' );
insert into pessoa ( id, nome, telefone, email ) values ( 8, 'Paulo', '(00) 09940-9587', 'paulo@sociedadefeliz.com.br' );

insert into animal ( id, nome, especie, raca, data_nascimento ) values ( 1, 'Tobby', 'CANIDEOS', 'pinscher', '2016-01-05' );
insert into animal ( id, nome, especie, raca, data_nascimento ) values ( 2, 'Flor', 'CANIDEOS', 'pinscher', '2018-07-26' );

insert into veterinario ( pessoa_id ) values ( 1 );
insert into veterinario ( pessoa_id ) values ( 2 );
insert into tutor ( pessoa_id, animal_id ) values ( 3, 1 );
insert into tutor ( pessoa_id, animal_id ) values ( 4, 1 );
insert into tutor ( pessoa_id, animal_id ) values ( 5, 1 );
insert into tutor ( pessoa_id, animal_id ) values ( 6, 1 );
insert into tutor ( pessoa_id, animal_id ) values ( 7, 2 );
insert into tutor ( pessoa_id, animal_id ) values ( 8, 2 );
