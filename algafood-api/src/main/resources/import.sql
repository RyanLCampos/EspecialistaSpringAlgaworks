
--- FORMA DE PAGAMENTO ---
INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'PIX');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'CARTÃO');


--- COZINHAS ---
INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Brasileira');


--- RESTAURANTES ---
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Gourmet', 10, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Thai Delivery', 9.55, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Sabor Brasileiro', 7.99, 1, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Churrasco Gaúcho', 12.50, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Temperos da Vovó', 5.90, 1, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Cantinho Mineiro', 8.25, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Delícias Nordestinas', 6.75, 1, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Peixaria do Mar', 10.30, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Comida de Boteco', 7.50, 1, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Fogão a Lenha', 9.00, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Esquina do Açaí', 4.99, 1, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Feijão Tropeiro', 6.80, 1, 2);

--- ESTADO ---
INSERT INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (4, 'Bahia');
INSERT INTO estado (id, nome) VALUES (5, 'Paraná');


--- CIDADE ---
INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Campinas', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Rio de Janeiro', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Niterói', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Belo Horizonte', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Uberlândia', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Salvador', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Feira de Santana', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Curitiba', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Londrina', 5);

--- PERMISSAO ---
INSERT INTO permissao (nome, descricao) VALUES ('VISUALIZAR_RESTAURANTE', 'Permissão para visualizar restaurantes');
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_RESTAURANTE', 'Permissão para editar dados de restaurantes');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_RESTAURANTE', 'Permissão para deletar restaurantes');
INSERT INTO permissao (nome, descricao) VALUES ('ADICIONAR_RESTAURANTE', 'Permissão para adicionar novos restaurantes');
INSERT INTO permissao (nome, descricao) VALUES ('VISUALIZAR_PEDIDOS', 'Permissão para visualizar pedidos');
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_PEDIDOS', 'Permissão para editar pedidos');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_PEDIDOS', 'Permissão para deletar pedidos');
INSERT INTO permissao (nome, descricao) VALUES ('ADICIONAR_USUARIO', 'Permissão para adicionar novos usuários');
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_USUARIO', 'Permissão para editar dados de usuários');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_USUARIO', 'Permissão para deletar usuários');