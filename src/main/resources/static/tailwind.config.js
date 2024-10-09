/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../templates/**/*.{html,js}",  "./node_modules/flowbite/**/*.js"],
  theme: {
      container: {
            center: true,
          },
      extend: {},
  },
  plugins: [ require('flowbite/plugin')],
}